/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.Utakmica;
import model.Kolo;
import servisi.UtakmicaServis;

/**
 *
 * @author lukovp
 */
@Named(value = "mbUtakmica")
@SessionScoped
public class MBUtakmica implements Serializable{
    
    @Inject
    private UtakmicaServis utakmicaServis;
    
    private Utakmica trenutnaUtakmica;

    public Utakmica getTrenutnaUtakmica() {
        return trenutnaUtakmica;
    }

    public void setTrenutnaUtakmica(Utakmica trenutnaUtakmica) {
        this.trenutnaUtakmica = trenutnaUtakmica;
    }       
    
    public List<Utakmica> vratiUtakmiceZaKolo(Kolo kolo){
        return utakmicaServis.vratiSveUtakmice(kolo);
    }
    
    public void azuriraj(Utakmica utakmica){
        utakmicaServis.obradiUtakmicu(utakmica);
        utakmicaServis.dodajPoeneUcesnika(utakmica);
        utakmicaServis.proglasiSampiona(utakmica);
    }
    
    public void validirajUtakmicu(ComponentSystemEvent event){
        utakmicaServis.validirajUtakmicu(event);
    }
    
}
