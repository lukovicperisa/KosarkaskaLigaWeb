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
import model.Drzava;
import servisi.DrzavaServis;

/**
 *
 * @author lukovp
 */
@Named(value = "drzavaKNV")
@RequestScoped
public class DrzavaKonverter implements Converter {

    @Inject
    private DrzavaServis drzavaServis;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        return drzavaServis.vratiDrzavuZaID(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((Drzava) value).getDrzavaId().toString();
    }
}
