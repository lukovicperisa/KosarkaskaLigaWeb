/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.UtakmicaDAO;
import dao.UcesnikDAO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import model.Utakmica;
import model.Ucesnik;
import model.Kolo;
import validators.UtakmicaValidator;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class UtakmicaServis {

    @EJB
    private UtakmicaDAO utakmicaDAO;

    @EJB
    private UcesnikDAO ucesnikDAO;

    @Inject
    private KoloServis koloServis;
    
    @Inject
    private SezonaServis sezonaServis;
    
    @Inject
    private UtakmicaValidator utakmicaValidator;

    ResourceBundle bundle = ResourceBundle.getBundle("resourceBundle.base");

    void kreirajUtakmice(Kolo kolo) {
        int brojUtakmica = kolo.getSezona().getListaUcesnika().size() / 2;
        List<Utakmica> utakmice = new ArrayList<>();
        for (int i = 1; i <= brojUtakmica; i++) {
            Utakmica utakmica = new Utakmica();
            utakmica.setUtakmicaID(generisiID());
            utakmica.setKolo(kolo);
            utakmicaDAO.sacuvaj(utakmica);
            utakmice.add(utakmica);
        }
        kolo.setListaUtakmica(utakmice);
        koloServis.azuriraj(kolo);
    }

    private List<Utakmica> vratiSveUtakmice() {
        return utakmicaDAO.vratiSveUtakmice();
    }

    private Integer generisiID() {
        List<Utakmica> utakmice = UtakmicaServis.this.vratiSveUtakmice();
        if (utakmice.isEmpty()) {
            return 1;
        }
        int poslednja = utakmice.get(utakmice.size() - 1).getUtakmicaID();
        return poslednja + 1;
    }

    public List<Utakmica> vratiSveUtakmice(Kolo kolo) {
        return utakmicaDAO.vratiUtakmiceZaKolo(kolo);
    }

    public void azuriraj(Utakmica utakmica) {
        utakmicaDAO.azuriraj(utakmica);
    }

    public void dodajPoeneUcesnika(Utakmica utakmica) {
        List<Ucesnik> ucesnici = ucesnikDAO.vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona());
        for (Ucesnik ucesnik : ucesnici) {
            if (Objects.equals(ucesnik.getKlub().getKlubID(), utakmica.getDomacin().getKlubID())) {
                ucesnik.setBrojOdigranihUtakmica(ucesnik.getBrojOdigranihUtakmica()+ 1);
                if (utakmica.getPoenaDomaci()> utakmica.getPoenaGosti()) {
                    ucesnik.setBrojPobeda(ucesnik.getBrojPobeda()+ 1);
                }
                ucesnikDAO.azuriraj(ucesnik);
            }
            if (Objects.equals(ucesnik.getKlub().getKlubID(), utakmica.getGost().getKlubID())) {
                ucesnik.setBrojOdigranihUtakmica(ucesnik.getBrojOdigranihUtakmica()+ 1);
                if (utakmica.getPoenaGosti()> utakmica.getPoenaDomaci()) {
                    ucesnik.setBrojPobeda(ucesnik.getBrojPobeda()+ 1);
                }
                ucesnikDAO.azuriraj(ucesnik);
            }
        }
    }

    public void validirajUtakmicu(ComponentSystemEvent event) {
        utakmicaValidator.validirajUtakmicu(event);
    }


    public void obradiUtakmicu(Utakmica utakmica) {
        Optional<Utakmica> trenutnaUtakmica = vratiSveUtakmice(utakmica.getKolo()).stream()
                .filter(g -> Objects.equals(g.getUtakmicaID(), utakmica.getUtakmicaID()))
                .findAny();

        if (trenutnaUtakmica.get().getPoenaDomaci()!= null) {
            List<Ucesnik> ucesnici = ucesnikDAO.vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona());

            Optional<Ucesnik> domacin = ucesnici.stream()
                    .filter(p -> Objects.equals(p.getKlub().getKlubID(), trenutnaUtakmica.get().getDomacin().getKlubID()))
                    .findAny();

            Optional<Ucesnik> gost = ucesnici.stream()
                    .filter(p -> Objects.equals(p.getKlub().getKlubID(), trenutnaUtakmica.get().getGost().getKlubID()))
                    .findAny();

            Ucesnik u1 = domacin.get();
            Ucesnik u2 = gost.get();
            u1.setBrojOdigranihUtakmica(u1.getBrojOdigranihUtakmica()- 1);
            u2.setBrojOdigranihUtakmica(u2.getBrojOdigranihUtakmica()- 1);

            if (trenutnaUtakmica.get().getPoenaDomaci()> trenutnaUtakmica.get().getPoenaGosti()) {
                u1.setBrojPobeda(u1.getBrojPobeda()- 1);
            } else {
                u2.setBrojPobeda(u2.getBrojPobeda()- 1);
            }
            ucesnikDAO.azuriraj(u1);
            ucesnikDAO.azuriraj(u2);
        }
        azuriraj(utakmica);
    }

    public void proglasiSampiona(Utakmica utakmica) {
        long preostaleUtakmice = vratiSveUtakmice().stream()
                .filter(g -> Objects.equals(g.getKolo().getSezona().getSezonaID(), utakmica.getKolo().getSezona().getSezonaID()))
                .filter(g -> g.getPoenaDomaci()==null)
                .count();
        
        if(preostaleUtakmice == 0){
            Optional<Ucesnik> sampion = ucesnikDAO.vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona()).stream()
                    .sorted(Comparator.comparingInt(Ucesnik::getBrojPobeda).reversed())
                    .findFirst();
            
            sezonaServis.proglasiSampiona(sampion);
        }
    }

}
