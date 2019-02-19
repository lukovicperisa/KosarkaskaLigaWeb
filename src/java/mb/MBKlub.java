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
import javax.servlet.http.Part;
import model.Klub;
import servisi.KlubServis;

/**
 *
 * @author lukovp
 */
@Named(value = "mbKlub")
@SessionScoped
public class MBKlub implements Serializable {

    @Inject
    private KlubServis klubServis;

    private List<Klub> klubovi;
    private Klub trenutniKlub;
    private String pobednickeSezone;
    
    public MBKlub() {
        trenutniKlub = new Klub();
    }

    public List<Klub> getKlubovi() {
        return klubServis.vratiSveKlubove();
    }

    public void setKlubovi(List<Klub> klubovi) {
        this.klubovi = klubovi;
    }

    public Klub getTrenutniKlub() {
        return trenutniKlub;
    }

    public void setTrenutniKlub(Klub trenutniKlub) {
        this.trenutniKlub = trenutniKlub;
    }

    public String getPobednickeSezone() {
        return klubServis.vratiPobednickeSezone(trenutniKlub);
    }

    public void setPobednickeSezone(String pobednickeSezone) {
        this.pobednickeSezone = pobednickeSezone;
    }

    public void sacuvaj(Part slika) throws Exception {
        if (slika != null) {
            klubServis.sacuvajSliku(trenutniKlub, slika);
        }
        klubServis.sacuvaj(trenutniKlub);
        resetujTrenutniKlub();
    }

    public void resetujTrenutniKlub() {
        trenutniKlub = new Klub();
    }

}
