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
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.Utakmica;
import model.Ucesnik;
import model.Kolo;
import model.Sezona;
import validatori.UtakmicaValidator;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class UtakmicaServis {
    
    private final static Logger LOG = Logger.getLogger(UtakmicaServis.class.getName());

    @Inject
    private UtakmicaDAO utakmicaDAO;

    @Inject
    private UcesnikDAO ucesnikDAO;

    @Inject
    private KoloServis koloServis;

    @Inject
    private SezonaServis sezonaServis;

    @Inject
    private UtakmicaValidator utakmicaValidator;

    ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");

    @Transactional
    private void sacuvajUtakmicu(Utakmica utakmica) {
        try {
            utakmicaDAO.sacuvaj(utakmica);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva utakmicu!");
        }
    }

    @Transactional
    private List<Utakmica> vratiSveUtakmice() {
        try{
            return utakmicaDAO.vratiSveUtakmice();
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje utakmice!");
            return null;
        }
    }

    @Transactional
    public List<Utakmica> vratiSveUtakmice(Kolo kolo) {
        try{
            return utakmicaDAO.vratiUtakmiceZaKolo(kolo);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje utakmice za zadati kriterijum!");
            return null;
        }
    }

    @Transactional
    public void azuriraj(Utakmica utakmica) {
        try {
            utakmicaDAO.azuriraj(utakmica);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva utakmicu!");
        }

    }
    
    @Transactional
    private List<Ucesnik> vratiUcesnikeZaSezonu(Sezona sezona) {
        try {
            return ucesnikDAO.vratiUcesnikeZaSezonu(sezona);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje ucesnike za zadati kriterijum!");
            return null;
        }
    }
    
    @Transactional
    private void azurirajUcesnika(Ucesnik ucesnik) {
        try{
            ucesnikDAO.azuriraj(ucesnik);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva ucesnika!");
        }

    }

    void kreirajUtakmice(Kolo kolo) {
        int brojUtakmica = kolo.getSezona().getListaUcesnika().size() / 2;
        List<Utakmica> utakmice = new ArrayList<>();
        for (int i = 1; i <= brojUtakmica; i++) {
            Utakmica utakmica = new Utakmica();
            utakmica.setUtakmicaID(generisiID());
            utakmica.setKolo(kolo);
            sacuvajUtakmicu(utakmica);
            utakmice.add(utakmica);
        }
        kolo.setListaUtakmica(utakmice);
        koloServis.azuriraj(kolo);
    }

    private Integer generisiID() {
        List<Utakmica> utakmice = UtakmicaServis.this.vratiSveUtakmice();
        if (utakmice.isEmpty()) {
            return 1;
        }
        int poslednja = utakmice.get(utakmice.size() - 1).getUtakmicaID();
        return poslednja + 1;
    }

    public void dodajPoeneUcesnika(Utakmica utakmica) {
        List<Ucesnik> ucesnici = vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona());
        for (Ucesnik ucesnik : ucesnici) {
            if (Objects.equals(ucesnik.getKlub().getKlubID(), utakmica.getDomacin().getKlubID())) {
                ucesnik.setBrojOdigranihUtakmica(ucesnik.getBrojOdigranihUtakmica()+ 1);
                if (utakmica.getPoenaDomaci()> utakmica.getPoenaGosti()) {
                    ucesnik.setBrojPobeda(ucesnik.getBrojPobeda()+ 1);
                }
                azurirajUcesnika(ucesnik);
            }
            if (Objects.equals(ucesnik.getKlub().getKlubID(), utakmica.getGost().getKlubID())) {
                ucesnik.setBrojOdigranihUtakmica(ucesnik.getBrojOdigranihUtakmica()+ 1);
                if (utakmica.getPoenaGosti()> utakmica.getPoenaDomaci()) {
                    ucesnik.setBrojPobeda(ucesnik.getBrojPobeda()+ 1);
                }
                azurirajUcesnika(ucesnik);
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

        if (trenutnaUtakmica.get().getPoenaDomaci() != null) {
            List<Ucesnik> ucesnici = vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona());

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
            azurirajUcesnika(u1);
            azurirajUcesnika(u2);
        }
        azuriraj(utakmica);
    }

    public void proglasiSampiona(Utakmica utakmica) {
        long preostaleUtakmice = vratiSveUtakmice().stream()
                .filter(g -> Objects.equals(g.getKolo().getSezona().getSezonaID(), utakmica.getKolo().getSezona().getSezonaID()))
                .filter(g -> g.getPoenaDomaci()==null)
                .count();

        if (preostaleUtakmice == 0) {
            Optional<Ucesnik> sampion = vratiUcesnikeZaSezonu(utakmica.getKolo().getSezona()).stream()
                    .sorted(Comparator.comparingInt(Ucesnik::getBrojPobeda).reversed())
                    .findFirst();

            sezonaServis.proglasiSampiona(sampion);
        }
    }

}
