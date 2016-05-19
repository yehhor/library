/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yehor
 */
@ManagedBean
@SessionScoped
public class User implements Serializable {

    private String name;
    private String password;
    /**
     * Creates a new instance of User
     */
    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String login()
    {
        try{
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).login(name, password);
            
            return "books";
            
            
        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext cntxt = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Логин и пароль не подходит");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            cntxt.addMessage("login_div", message);
        }
        return "index";
    }
    
    public String logout()
    {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            req.logout();

        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
