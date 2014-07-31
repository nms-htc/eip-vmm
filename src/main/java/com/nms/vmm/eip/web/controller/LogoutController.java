/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cuong
 */
@Named
@RequestScoped
public class LogoutController {

    public String logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        
        try {
            if (request.getUserPrincipal() != null) {
                request.logout();
            }
        } catch (ServletException e) {
            _logger.log(Level.SEVERE, "Logout error", e);
        }
        
        
        return "/index.xhtml?faces-redirect=true";
    }
    
    private static final Logger _logger = Logger.getLogger(LogoutController.class.getName());
}
