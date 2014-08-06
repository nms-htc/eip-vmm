/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.AbstractFacade;
import com.nms.vmm.eip.ejb.UserEntryFacade;
import com.nms.vmm.eip.entity.UserEntry;
import com.nms.vmm.eip.entity.UserRole;
import com.nms.vmm.eip.web.util.JsfUtil;
import com.nms.vmm.eip.web.util.MessageUtil;
import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cuong
 */
@Named
@SessionScoped
public class UserEntryController extends AbstractController<UserEntry> implements Serializable {

    private static final long serialVersionUID = 8571506113105478642L;

    @EJB
    private UserEntryFacade facade;

    public UserEntryController() {
        super(UserEntry.class);
    }

    @Override
    protected AbstractFacade<UserEntry> getFacade() {
        return facade;
    }
    
    public SelectItem[] getRoleSelectItems() {
        SelectItem[] selectItems = new SelectItem[UserRole.values().length];
        for (int i = 0; i < UserRole.values().length; i++) {
            selectItems[i]= new SelectItem(UserRole.values()[i], UserRole.values()[i].name());
        }
        
        return selectItems;
    }
    
    public UserEntry getUserFromRequest() {
        UserEntry user = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        
        if (principal != null) {
            user = facade.find(principal.getName());
        }
        
        return user;
    }

    @Override
    protected boolean validate(UserEntry entity) {
        UserEntry userEntry = null;
        
        try {
            userEntry = facade.findByCode(entity.getCode());
        } catch (Exception e) {
            // valid
        }
        
        if (userEntry != null) {
            JsfUtil.addErrorMessage(MessageUtil.getBundleMessage("UserEntryCodeHasExits"));
            return false;
        }
        
        try {
            userEntry = facade.find(entity.getEmail());
        } catch (Exception e) {
            // valid
        }
        
        if (userEntry != null) {
            JsfUtil.addErrorMessage(MessageUtil.getBundleMessage("UserEntryEmailHasExits"));
            return false;
        }
        
        return true;
    }
}
