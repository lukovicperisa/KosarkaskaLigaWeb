/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KorisnikDAO;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.Korisnik;
import org.apache.commons.lang3.StringUtils;
import util.PorukaUtil;

/**
 *
 * @author LUP1BG
 */
@ApplicationScoped
public class KorisnikServis {
    
    private final static Logger LOG = Logger.getLogger(KorisnikServis.class.getName());

    @Inject
    private KorisnikDAO korisnikDAO;
    
    @Transactional
    private Korisnik vratiKorisnikaZaID(String id){
        try {
        return korisnikDAO.vratiKorisnikaZaID(id);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje korisnika za zadati kriterijum");
            return null;
        }
    }

    public Korisnik prijaviKorisnika(String korisnickoIme, String lozinka) {

        Korisnik korisnik = vratiKorisnikaZaID(korisnickoIme);

        if (korisnik == null) {
            PorukaUtil.dodajPoruku("statusPrijavljivanja", "validacija.korisnik.nepostojeci");
            return new Korisnik();
        }

        return proveriLozinkuZaKorisnika(korisnik, lozinka);
    }

    private Korisnik proveriLozinkuZaKorisnika(Korisnik korisnik, String lozinka) {
        if (!StringUtils.equals(korisnik.getLozinka(), lozinka)) {
            PorukaUtil.dodajPoruku("statusPrijavljivanja", "validacija.korisnik.lozinka");
            return new Korisnik();
        }
        return korisnik;
    }

}
