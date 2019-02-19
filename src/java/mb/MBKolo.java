/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Kolo;
import model.Sezona;
import servisi.KoloServis;

/**
 *
 * @author lukovp
 */
@Named(value = "mbKolo")
@ViewScoped
public class MBKolo implements Serializable{
    
    @Inject
    private KoloServis koloServis;

    private List<Kolo> kola;
    private Kolo trenutnoKolo;

    public MBKolo() {
        this.trenutnoKolo = new Kolo();
    }
    
    public List<Kolo> getKola() {
        return kola;
    }

    public void setKola(List<Kolo> kola) {
        this.kola = kola;
    }

    public Kolo getTrenutnoKolo() {
        return trenutnoKolo;
    }

    public void setTrenutnoKolo(Kolo trenutnoKolo) {
        this.trenutnoKolo = trenutnoKolo;
    }
    
    public List<Kolo> vratiKola(Sezona sezona){
        return koloServis.vratiKolaZaSezonu(sezona);
    }
    
    public void azuriraj(){
        koloServis.azuriraj(trenutnoKolo);
    }
    
    public void sacuvaj(Kolo kolo){
        koloServis.sacuvaj(kolo);
    }
    
    public void resetujKolo() {
        trenutnoKolo = new Kolo();
    }
}
