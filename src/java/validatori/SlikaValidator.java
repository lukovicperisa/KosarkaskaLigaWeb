/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author lukovp
 */
@FacesValidator("slikaValidator")
public class SlikaValidator extends AbstraktniValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        if (file != null) {
            if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
                throw new ValidatorException(new FacesMessage(bundle.getString("validacija.klub.slika.poruka")));
            }
        }
    }
}
