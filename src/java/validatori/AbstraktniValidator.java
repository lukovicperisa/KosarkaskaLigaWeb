/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author LUP1BG
 */
public abstract class AbstraktniValidator {
    
    ResourceBundle bundle = ResourceBundle.getBundle("rb.prevodi");
    
    protected String vratiKomponentuKaoString(UIInput komponenta) {
        return komponenta.getLocalValue() == null ? "" : komponenta.getLocalValue().toString();
    }

    protected void dodajPoruku(String poljeIspisa, String poruka) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(bundle.getString(poruka));
        fc.addMessage(poljeIspisa, msg);
        fc.renderResponse();
    }
    
}
