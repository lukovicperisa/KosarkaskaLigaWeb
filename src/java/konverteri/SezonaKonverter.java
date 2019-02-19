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
import model.Sezona;
import servisi.SezonaServis;

/**
 *
 * @author lukovp
 */
@Named(value = "sezonaKNV")
@RequestScoped
public class SezonaKonverter implements Converter {

    @Inject
    private SezonaServis sezonaServis;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        return sezonaServis.vratiSezonuZaID(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((Sezona) value).getSezonaID().toString();
    }
}
