/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Klub;
import model.Ucesnik;
import model.Sezona;
import servisi.SezonaServis;

/**
 *
 * @author lukovp
 */
@Named(value = "mbSezona")
@ViewScoped
public class MBSezona implements Serializable {
    
    @Inject
    private SezonaServis sezonaServis;
    
    private List<Sezona> sezone;
    private Sezona trenutnaSezona;
    private List<Ucesnik> rangLista;
    private List<Klub> klubovi;
    
    public MBSezona() {
        trenutnaSezona = new Sezona();
    }
    
    public List<Klub> getKlubovi() {
        return klubovi;
    }

    public void setKlubovi(List<Klub> klubovi) {
        this.klubovi = klubovi;
    }
    
    public List<Ucesnik> getRangLista() {
        return sezonaServis.vratiRangiranje(trenutnaSezona);
    }

    public void setRangLista(List<Ucesnik> rangLista) {
        this.rangLista = rangLista;
    }

    public List<Sezona> getSezone() {
        return sezonaServis.vratiSveSezone();
    }

    public void setSezone(List<Sezona> sezone) {
        this.sezone = sezone;
    }

    public Sezona getTrenutnaSezona() {
        return trenutnaSezona;
    }

    public void setTrenutnaSezona(Sezona trenutnaSezona) {
        this.trenutnaSezona = trenutnaSezona;
    }

    public void sacuvaj() {
        sezonaServis.sacuvaj(trenutnaSezona,klubovi);
        resetujTrenutnuSezonu();
    }

    public void resetujTrenutnuSezonu() {
        trenutnaSezona = new Sezona();
    }
    
    public void validirajDatume(ComponentSystemEvent event){
        sezonaServis.validirajDatume(event);
    }
    
}
