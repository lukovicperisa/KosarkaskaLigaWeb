/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servisi;

import dao.KlubDAO;
import dao.SezonaDAO;
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
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import model.Klub;

/**
 *
 * @author lukovp
 */
@ApplicationScoped
public class KlubServis {

    @EJB
    private KlubDAO klubDAO;
    
    @EJB
    private SezonaDAO sezonaDAO;
    
    private final ResourceBundle bundle = ResourceBundle.getBundle("resourceBundle.base");

    public List<Klub> vratiSveKlubove() {
        return klubDAO.vratiSveKlubove();
    }

    public Klub vratiKlubZaID(int id) {
        return klubDAO.vratiKlubZaID(id);
    }

    public void sacuvaj(Klub klub) {
        if (vratiSveKlubove().contains(klub)) {
            klubDAO.azuriraj(klub);
        } else {
            klub.setKlubID(generisiID());
            klubDAO.sacuvaj(klub);
        }
        dodajPoruku("clubStatus","club.registration.status.message");
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
            Path slika = new File(bundle.getString("site.images.folder"), img.getSubmittedFileName()).toPath();
            Files.copy(img.getInputStream(), slika, StandardCopyOption.REPLACE_EXISTING);
            klub.setSlika("img/" + img.getSubmittedFileName());
        } catch (IOException ex) {
            Logger.getLogger(KlubServis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String vratiPobednickeSezone(Klub klub) {
        return sezonaDAO.vratiSveSezone().stream()
                .filter(s -> s.getSampion()!=null)
                .filter(s -> Objects.equals(s.getSampion().getKlubID(), klub.getKlubID()))
                .map(s -> s.getNazivLige())
                .collect(Collectors.joining(", "));
    }

    private void dodajPoruku(String komponentaID, String poruka) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        UIInput klijentID = (UIInput) ctx.getViewRoot().findComponent(komponentaID);
        String ispis = klijentID.getClientId();
        ctx.addMessage(ispis, new FacesMessage(bundle.getString(poruka)));
    }

}
