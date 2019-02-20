/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KorisnikDAO;
import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Korisnik;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class KorisnikServis {

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");

    @Inject
    private KorisnikDAO korisnikDAO;

    public Korisnik prijaviKorisnika(String korisnickoIme, String lozinka) {

        Korisnik korisnik = korisnikDAO.vratiKorisnikaZaID(korisnickoIme);

        if (korisnik == null) {
            postaviPoruku("statusPrijavljivanja", "validacija.korisnik.nepostojeci");
            return new Korisnik();
        }

        return proveriLozinkuZaKorisnika(korisnik, lozinka);
    }

    private Korisnik proveriLozinkuZaKorisnika(Korisnik korisnik, String lozinka) {
        if (!StringUtils.equals(korisnik.getLozinka(), lozinka)) {
            postaviPoruku("statusPrijavljivanja", "validacija.korisnik.lozinka");
            return new Korisnik();
        }
        return korisnik;
    }

    private void postaviPoruku(String komponentaID, String poruka) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        UIInput klijentID = (UIInput) ctx.getViewRoot().findComponent(komponentaID);
        String ispis = klijentID.getClientId();
        ctx.addMessage(ispis, new FacesMessage(bundle.getString(poruka)));
    }

}
