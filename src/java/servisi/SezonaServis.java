/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.UcesnikDAO;
import dao.SezonaDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import mb.MBSezona;
import model.Klub;
import model.Ucesnik;
import model.Sezona;
import util.PorukaUtil;
import validatori.DatumValidator;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class SezonaServis {
    
    private final static Logger LOG = Logger.getLogger(SezonaServis.class.getName());

    @Inject
    private KoloServis koloServis;

    @Inject
    private SezonaDAO sezonaDAO;

    @Inject
    private UcesnikDAO ucesnikDAO;

    @Inject
    private MBSezona mbSezona;

    @Inject
    private DatumValidator datumValidator;

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");

    public List<Sezona> vratiSveSezone() {
        try {
            return sezonaDAO.vratiSveSezone();
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje sezone!");
            return null;
        }
    }

    @Transactional
    public Sezona vratiSezonuZaID(Integer id) {
        try {
            return sezonaDAO.vratiSezonuZaID(id);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje sezonu za zadati kriterijum!");
            return null;
        }
    }

    @Transactional
    private void sacuvajSezonu(Sezona sezona) {
        try {
            sezonaDAO.sacuvaj(sezona);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva sezonu!");
            PorukaUtil.dodajPoruku("statusNeuspeh", "liga.registracija.neuspeh");
        }
    }

    @Transactional
    private void izmeniSezonu(Sezona sezona) {
        try {
            sezonaDAO.azuriraj(sezona);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva sezonu!");
            PorukaUtil.dodajPoruku("statusNeuspeh", "liga.registracija.neuspeh");
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
        sacuvajSezonu(sezona);
        koloServis.kreirajKola(sezona);
        koloServis.generisiRaspored(sezona);
        PorukaUtil.dodajPoruku("statusRegistracije", "liga.registracija.status.poruka");
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
        List<Ucesnik> ucesnici = vratiUcesnikeZaSezonu(sezona);

        Collections.sort(ucesnici, (o1, o2) -> {
            return vratiPoene(o1) > vratiPoene(o2) ? -1 : vratiPoene(o1) < vratiPoene(o2) ? 1 : 0;
        });

        return ucesnici;
    }

    public void validirajDatume(ComponentSystemEvent event) {
        datumValidator.validirajDatume(event);
    }

    private int vratiPoene(Ucesnik ucesnik) {
        return ucesnik.getBrojOdigranihUtakmica() + ucesnik.getBrojPobeda();
    }

    void proglasiSampiona(Optional<Ucesnik> sampion) {
        Sezona trenutnaSezona = sampion.get().getSezona();
        trenutnaSezona.setSampion(sampion.get().getKlub());
        izmeniSezonu(trenutnaSezona);
        mbSezona.setTrenutnaSezona(trenutnaSezona);
    }

    public void sacuvajSliku(Sezona sezona, Part img) {
        try {
            Path slika = new File(bundle.getString("sajt.slike.folder"), img.getSubmittedFileName()).toPath();
            Files.copy(img.getInputStream(), slika, StandardCopyOption.REPLACE_EXISTING);
            sezona.setSlika("img/" + img.getSubmittedFileName());
        } catch (IOException ex) {
            LOG.warning("Slika nije sacuvana!");
        }
    }

}
