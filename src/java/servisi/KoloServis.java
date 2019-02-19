/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KoloDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
    
    @EJB
    private KoloDAO koloDAO;
    
    @Inject
    private UtakmicaServis utakmicaServis;
    
    public List<Kolo> vratiSvaKola(){
        return koloDAO.vratiSvaKola();
    }

    public void kreirajKola(Sezona sezona) {
        int brojKola = (sezona.getListaUcesnika().size()-1)*sezona.getSistem();
        List<Kolo> kola = new ArrayList<>();
        for(int i=0; i<brojKola; i++){
            Kolo kolo = new Kolo();
            kolo.setKoloID(generisiID());
            kolo.setSezona(sezona);
            kolo.setBrojKola(i);
            koloDAO.sacuvaj(kolo);
            kola.add(kolo);
            utakmicaServis.kreirajUtakmice(kolo);
        }
        sezona.setListaKola(kola);
    }
    
    public List<Kolo> vratiKolaZaSezonu(Sezona sezona) {
        return koloDAO.vratiKolaZaSezonu(sezona);
    }
    
    private Integer generisiID() {
        List<Kolo> kola = vratiSvaKola();
        if(kola.isEmpty()){
            return 1;
        }
        int last = kola.get(kola.size() - 1).getKoloID();
        return last + 1;
    }   

    public void azuriraj(Kolo kolo) {
        koloDAO.azuriraj(kolo);
    }

    public void sacuvaj(Kolo kolo) {
        koloDAO.sacuvaj(kolo);
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

    public Kolo vratiKoloZaID(Integer id) {
        return koloDAO.vratiKoloZaID(id);
    }
}
