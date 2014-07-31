/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.UserEntry;
import com.nms.vmm.eip.web.util.AppUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cuong
 */
@Stateless
public class UserEntryFacade extends AbstractFacade<UserEntry> implements Serializable {

    private static final long serialVersionUID = 8105388844447106807L;

    @PersistenceContext
    private EntityManager em;

    public UserEntryFacade() {
        super(UserEntry.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(UserEntry entity) {
        try {
            entity.setPassword(AppUtil.encodeSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserEntryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.create(entity);
    }

    @Override
    public void edit(UserEntry entity) {
         try {
            entity.setPassword(AppUtil.encodeSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserEntryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.edit(entity);
    }
}
