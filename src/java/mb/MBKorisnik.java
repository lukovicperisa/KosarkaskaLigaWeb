/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Korisnik;
import org.apache.commons.lang3.StringUtils;
import servisi.KorisnikServis;
import util.Stranice;

/**
 *
 * @author LUP1BG
 */
@Named("mbKorisnik")
@SessionScoped
public class MBKorisnik implements Serializable {

    @Inject
    private KorisnikServis korisnikServis;

    private Korisnik trenutniKorisnik;

    public MBKorisnik() {
        trenutniKorisnik = new Korisnik();
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik = trenutniKorisnik;
    }

    public String prijaviKorisnika() {
        trenutniKorisnik = korisnikServis.prijaviKorisnika(trenutniKorisnik.getKorisnickoIme(), trenutniKorisnik.getLozinka());
        if(StringUtils.isNotBlank(trenutniKorisnik.getKorisnickoIme())){
            return Stranice.INDEX;
        }
        return null;
    }

    public void odjaviKorisnika() {
        trenutniKorisnik = new Korisnik();
    }

}
