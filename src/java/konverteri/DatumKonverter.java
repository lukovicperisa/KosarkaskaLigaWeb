/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author lukovp
 */
@Named(value = "datumKNV")
@RequestScoped
public class DatumKonverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return format.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(DatumKonverter.class.getName()).log(Level.WARNING, "Vrednost nije u odgovarajucem formatu");
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(value);
    }
}
