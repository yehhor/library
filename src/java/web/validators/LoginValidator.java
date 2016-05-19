/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author yehor
 */
@FacesValidator("web.validators.LoginValidator")
public class LoginValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        ResourceBundle rb = ResourceBundle.getBundle("web.properties.messages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());

        if (value.toString().length() == 0 || !Character.isLetter(value.toString().charAt(0))) {
            FacesMessage msg = new FacesMessage(rb.getString("login_start_error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (value.toString().equals("username") || value.toString().equals("login")) {
            FacesMessage msg = new FacesMessage(rb.getString("login_username_error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (value.toString().length() < 3) {
            FacesMessage msg = new FacesMessage(rb.getString("login_length_error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
