/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KlubDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import model.Klub;
import model.Sezona;
import model.Utakmica;
import util.PorukaUtil;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class KlubServis {

    private final static Logger LOG = Logger.getLogger(KlubServis.class.getName());

    @Inject
    private KlubDAO klubDAO;

    @Inject
    private SezonaServis sezonaServis;

    private final ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");

    @Transactional
    public List<Klub> vratiSveKlubove() {
        try {
            return klubDAO.vratiSveKlubove();
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje klubove");
            return null;
        }
    }

    @Transactional
    public Klub vratiKlubZaID(int id) {
        try {
            return klubDAO.vratiKlubZaID(id);
        } catch (Exception ex) {
            LOG.warning("Sistem nije mogao da pronadje klub za zadati kriterijum");
            return null;
        }
    }

    @Transactional
    private void sacuvajKlub(Klub klub) {
        try {
            klubDAO.sacuvaj(klub);
            PorukaUtil.dodajPoruku("statusUspeh", "klub.registracija.status.poruka");
        } catch (Exception ex) {
            LOG.warning("Klub nije sacuvan");
            PorukaUtil.dodajPoruku("statusNeuspeh", "klub.registracija.neuspeh.poruka");
        }
    }

    @Transactional
    private void izmeniKlub(Klub klub) {
        try {
            klubDAO.azuriraj(klub);
            PorukaUtil.dodajPoruku("statusUspeh", "klub.registracija.status.poruka");
        } catch (Exception ex) {
            LOG.warning("Klub nije sacuvan");
            PorukaUtil.dodajPoruku("statusNeuspeh", "klub.registracija.neuspeh.poruka");
        }
    }

    public void sacuvaj(Klub klub) {
        if (vratiSveKlubove().contains(klub)) {
            izmeniKlub(klub);
        } else {
            klub.setKlubID(generisiID());
            sacuvajKlub(klub);
        }
    }

    private Integer generisiID() {
        List<Klub> klubovi = vratiSveKlubove();
        if (klubovi.isEmpty()) {
            return 1;
        }
        int last = klubovi.get(klubovi.size() - 1).getKlubID();
        return last + 1;
    }

    public void sacuvajSliku(Klub klub, Part img) {
        try {
            Path slika = new File(bundle.getString("sajt.slike.folder"), img.getSubmittedFileName()).toPath();
            Files.copy(img.getInputStream(), slika, StandardCopyOption.REPLACE_EXISTING);
            klub.setSlika("img/" + img.getSubmittedFileName());
        } catch (IOException ex) {
            Logger.getLogger(KlubServis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String vratiPobednickeSezone(Klub klub) {
        return sezonaServis.vratiSveSezone().stream()
                .filter(s -> s.getSampion()!=null)
                .filter(s -> Objects.equals(s.getSampion().getKlubID(), klub.getKlubID()))
                .map(s -> s.getNazivLige())
                .collect(Collectors.joining(", "));
    }

    public String vratiKosRazlikuKluba(Klub klub, Sezona sezona) {

        int poenaDato = vratiPoeneDomacegTima(klub.getListaUtakmicaDomacin(), sezona) + vratiPoeneGostujucegTima(klub.getListaUtakmicaGost(), sezona);

        int poenaPrimljeno = vratiPoeneGostujucegTima(klub.getListaUtakmicaDomacin(), sezona) + vratiPoeneDomacegTima(klub.getListaUtakmicaGost(), sezona);

        return String.format("%d:%d", poenaDato, poenaPrimljeno);

    }
    
    private int vratiPoeneDomacegTima(List<Utakmica> utakmice, Sezona sezona) {
        return utakmice.stream()
                .filter(utakmica -> utakmica.getKolo().getSezona().getSezonaID().intValue() == sezona.getSezonaID())
                .mapToInt(utakmica -> utakmica.getPoenaDomaci())
                .sum();
    }
    
    private int vratiPoeneGostujucegTima(List<Utakmica> utakmice, Sezona sezona) {
        return utakmice.stream()
                .filter(utakmica -> utakmica.getKolo().getSezona().getSezonaID().intValue() == sezona.getSezonaID())
                .mapToInt(utakmica -> utakmica.getPoenaGosti())
                .sum();
    }

}
