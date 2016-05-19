/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yehor
 */
@ManagedBean
@RequestScoped
public class LoginController {

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    
    public String check()
    {
        return "books";
    }
    

}
