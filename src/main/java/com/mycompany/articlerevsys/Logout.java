/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nagarjun Prem
 */
@ManagedBean
@SessionScoped
public class Logout {
   
public String logout()
    {
         /*FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.invalidate();
         */
        
        /*FacesContext context = FacesContext.getCurrentInstance();
	    	context.getExternalContext().invalidateSession();
	        try {
				context.getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session!=null)
        {
            session.invalidate();
        }
        return "login.xhtml?faces-redirect=true";
        
    }
}

