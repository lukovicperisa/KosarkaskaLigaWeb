/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KoloDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.Klub;
import model.Utakmica;
import model.Kolo;
import model.Sezona;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class KoloServis {

    private final static Logger LOG = Logger.getLogger(KoloServis.class.getName());

    @Inject
    private KoloDAO koloDAO;

    @Inject
    private UtakmicaServis utakmicaServis;

    @Transactional
    private List<Kolo> vratiSvaKola() {
        try {
            return koloDAO.vratiSvaKola();
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje kola!");
            return null;
        }
    }

    @Transactional
    public List<Kolo> vratiKolaZaSezonu(Sezona sezona) {
        try {
            return koloDAO.vratiKolaZaSezonu(sezona);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje kola za zadati kriterijum!");
            return null;
        }
    }

    @Transactional
    public Kolo vratiKoloZaID(Integer id) {
        try {
            return koloDAO.vratiKoloZaID(id);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje kolo za zadati kriterijum!");
            return null;
        }
    }

    @Transactional
    public void azuriraj(Kolo kolo) {
        try {
            koloDAO.azuriraj(kolo);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva kolo!");
        }
    }

    @Transactional
    public void sacuvaj(Kolo kolo) {
        try {
            koloDAO.sacuvaj(kolo);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da sacuva kolo!");
        }
    }

    public void kreirajKola(Sezona sezona) {
        int brojKola = (sezona.getListaUcesnika().size()-1)*sezona.getSistem();
        List<Kolo> kola = new ArrayList<>();
        for(int i=0; i<brojKola; i++){
            Kolo kolo = new Kolo();
            kolo.setKoloID(generisiID());
            kolo.setSezona(sezona);
            kolo.setBrojKola(i);
            sacuvaj(kolo);
            kola.add(kolo);
            utakmicaServis.kreirajUtakmice(kolo);
        }
        sezona.setListaKola(kola);
    }
    
    private Integer generisiID() {
        List<Kolo> kola = vratiSvaKola();
        if(kola.isEmpty()){
            return 1;
        }
        int last = kola.get(kola.size() - 1).getKoloID();
        return last + 1;
    }

    void generisiRaspored(Sezona sezona) {
        List<Kolo> kola = sezona.getListaKola();
        List<Klub> klubovi = new ArrayList<>();
        sezona.getListaUcesnika().forEach((participant) -> {
            klubovi.add(participant.getKlub());
        });
        Klub prvi = klubovi.get(0);
        int sredina = klubovi.size()/2;

        List<Klub> timovi = klubovi;
        timovi.remove(0);

        for(Kolo kolo : kola){
            List<Utakmica> utakmice = kolo.getListaUtakmica();
            int timID = kolo.getBrojKola()% timovi.size();

            utakmice.get(0).setDomacin(prvi);
            utakmice.get(0).setGost(timovi.get(timID));

            utakmicaServis.azuriraj(utakmice.get(0));

            for(int i=1; i<sredina; i++){
                int prviTim = (kolo.getBrojKola()+ i) % timovi.size();
                int drugiTim = (kolo.getBrojKola()+ timovi.size() - i) % timovi.size();
                utakmice.get(i).setDomacin(timovi.get(prviTim));
                utakmice.get(i).setGost(timovi.get(drugiTim));

                utakmicaServis.azuriraj(utakmice.get(i));
            }
        }
    }

}
