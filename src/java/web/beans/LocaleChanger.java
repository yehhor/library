/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author yehor
 */
@ManagedBean
@SessionScoped
public class LocaleChanger implements Serializable {
    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public LocaleChanger() {
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    public void changeLocale(String l)
    {
        currentLocale = new Locale(l);
    }
}
