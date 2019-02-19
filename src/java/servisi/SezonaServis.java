/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.UcesnikDAO;
import dao.SezonaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import mb.MBSezona;
import model.Klub;
import model.Ucesnik;
import model.Sezona;
import validatori.DatumValidator;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class SezonaServis {

    @Inject
    private KoloServis koloServis;

    @EJB
    private SezonaDAO sezonaDAO;

    @EJB
    private UcesnikDAO ucesnikDAO;

    @Inject
    private MBSezona mbSezona;
    
    @Inject
    private DatumValidator datumValidator;
    
    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");

    public List<Sezona> vratiSveSezone() {
        return sezonaDAO.vratiSveSezone();
    }

    public Sezona vratiSezonuZaID(Integer id) {
        return sezonaDAO.vratiSezonuZaID(id);
    }

    public void sacuvaj(Sezona sezona, List<Klub> klubovi) {
        sezona.setSezonaID(generisiID());
        List<Ucesnik> ucesnici = new ArrayList<>();
        for (Klub klub : klubovi) {
            Ucesnik ucesnik = new Ucesnik(sezona.getSezonaID(), klub.getKlubID());
            ucesnik.setKlub(klub);
            ucesnik.setSezona(sezona);
            ucesnik.setBrojOdigranihUtakmica(0);
            ucesnik.setBrojPobeda(0);
            ucesnici.add(ucesnik);
        }
        sezona.setListaUcesnika(ucesnici);
        sezonaDAO.sacuvaj(sezona);
        koloServis.kreirajKola(sezona);
        koloServis.generisiRaspored(sezona);
        postaviPoruku("statusRegistracije","liga.registracija.status.poruka");
    }

    private Integer generisiID() {
        List<Sezona> sezone = vratiSveSezone();
        if (sezone.isEmpty()) {
            return 1;
        }
        int last = sezone.get(sezone.size() - 1).getSezonaID();
        return last + 1;
    }

    public List<Ucesnik> vratiRangiranje(Sezona sezona) {
        List<Ucesnik> ucesnici = ucesnikDAO.vratiUcesnikeZaSezonu(sezona);

        Collections.sort(ucesnici, (o1, o2) -> {
            return vratiPoene(o1) > vratiPoene(o2) ? -1 : vratiPoene(o1) < vratiPoene(o2) ? 1 : 0;
        });

        return ucesnici;
    }

    public void validirajDatume(ComponentSystemEvent event) {
        datumValidator.validirajDatume(event);
    }

    private int vratiPoene(Ucesnik ucesnik) {
        return ucesnik.getBrojOdigranihUtakmica()+ ucesnik.getBrojPobeda();
    }

    void proglasiSampiona(Optional<Ucesnik> sampion) {
        Sezona trenutnaSezona = sampion.get().getSezona();
        trenutnaSezona.setSampion(sampion.get().getKlub());
        sezonaDAO.azuriraj(trenutnaSezona);
        mbSezona.setTrenutnaSezona(trenutnaSezona);
    }
    
    private void postaviPoruku(String komponentaID, String poruka) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        UIInput klijentID = (UIInput) ctx.getViewRoot().findComponent(komponentaID);
        String ispis = klijentID.getClientId();
        ctx.addMessage(ispis, new FacesMessage(bundle.getString(poruka)));
    }

}
