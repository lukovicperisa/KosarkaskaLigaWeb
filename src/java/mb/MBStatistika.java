/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dto.Statistika;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Utakmica;
import servisi.StatistikaServis;

/**
 *
 * @author LUP1BG
 */
@Named(value = "mbStatistika")
@SessionScoped
public class MBStatistika implements Serializable {

    @Inject
    private StatistikaServis statistikaServis;

    private Statistika statistika;

    public Statistika getStatistika() {
        return statistika;
    }

    public void setStatistika(Statistika statistika) {
        this.statistika = statistika;
    }

    public Statistika vratiStatistikuZaUtakmicu(Utakmica utakmica) {
        if (statistika == null) {
            statistika = statistikaServis.vratiStatistikuUtakmice(utakmica);
        }
        return statistika;
    }

}
