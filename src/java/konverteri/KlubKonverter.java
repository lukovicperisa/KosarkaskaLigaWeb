/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import model.Klub;
import servisi.KlubServis;

/**
 *
 * @author lukovp
 */
@Named(value = "klubKNV")
@RequestScoped
public class KlubKonverter implements Converter {

    @Inject
    private KlubServis klubServis;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        return klubServis.vratiKlubZaID(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((Klub)value).getKlubID().toString();
    }    
}
