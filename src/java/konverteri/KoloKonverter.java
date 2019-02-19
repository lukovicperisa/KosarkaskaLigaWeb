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
import model.Kolo;
import servisi.KoloServis;

/**
 *
 * @author lukovp
 */
@Named(value = "koloKNV")
@RequestScoped
public class KoloKonverter implements Converter {

    @Inject
    private KoloServis koloServis;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        return koloServis.vratiKoloZaID(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return ((Kolo) value).getKoloID().toString();
    }
}
