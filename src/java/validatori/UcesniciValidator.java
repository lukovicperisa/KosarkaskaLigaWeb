/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import model.Klub;

/**
 *
 * @author LUP1BG
 */
@FacesValidator("ucesniciValidator")
public class UcesniciValidator extends AbstraktniValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        List<Klub> klubovi = (List<Klub>) value;
        if (klubovi != null) {
            int brojKlubova = klubovi.size();
            if (brojKlubova == 0) {
                throw new ValidatorException(new FacesMessage(bundle.getString("validation.league.participants.required")));
            }
            if (brojKlubova < 4) {
                throw new ValidatorException(new FacesMessage(bundle.getString("validation.league.participants.minimum")));
            }
            if (brojKlubova > 16) {
                throw new ValidatorException(new FacesMessage(bundle.getString("validation.league.participants.maximum")));
            }
            if (brojKlubova % 2 != 0) {
                throw new ValidatorException(new FacesMessage(bundle.getString("validation.league.participants.even")));
            }
        }
    }

}
