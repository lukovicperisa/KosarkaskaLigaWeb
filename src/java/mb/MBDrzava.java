/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Drzava;
import servisi.DrzavaServis;

/**
 *
 * @author lukovp
 */

@Named(value = "mbDrzava")
@SessionScoped
public class MBDrzava implements Serializable{
    
    @Inject
    private DrzavaServis drzavaServis;
    
    private List<Drzava> drzave;

    public List<Drzava> getDrzave() {
        return drzavaServis.vratiSveDrzave();
    }

    public void setDrzave(List<Drzava> drzave) {
        this.drzave = drzave;
    }
}
