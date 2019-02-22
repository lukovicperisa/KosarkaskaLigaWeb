/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.Igrac;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Klub;
import servisi.IgracServis;

/**
 *
 * @author LUP1BG
 */
@Named(value = "mbIgrac")
@SessionScoped
public class MBIgrac implements Serializable {

    @Inject
    private IgracServis igracServis;

    private List<Igrac> igraci;

    public List<Igrac> getIgraci() {
        return igraci;
    }

    public void setIgraci(List<Igrac> igraci) {
        this.igraci = igraci;
    }

    public List<Igrac> vratiIgraceZaKlub(Klub klub) {
        if (igraci == null) {
            igraci = igracServis.vratiIgraceZaKlub(klub);
        }
        return igraci;
    }

}
