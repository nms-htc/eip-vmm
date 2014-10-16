/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.entity.UserEntry_;
import com.nms.vmm.eip.entity.UserRole;
import com.nms.vmm.eip.web.util.AppUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cuong
 */
@Stateless
public class UserEntryFacade extends AbstractFacade<User> implements Serializable {

    private static final long serialVersionUID = 8105388844447106807L;

    @PersistenceContext
    private EntityManager em;

    public UserEntryFacade() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(User entity) {
        try {
            entity.setPassword(AppUtil.encodeSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserEntryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.create(entity);
    }

    @Override
    public void edit(User entity) {
         try {
            entity.setPassword(AppUtil.encodeSHA256(entity.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserEntryFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.edit(entity);
    }
    
    public User findByCode(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        
        cq.where(cb.equal(root.get(UserEntry_.code), code));
        
        TypedQuery<User> q = em.createQuery(cq);
        
        return q.getSingleResult();
    }
    
    public int countAdministrator() {
        TypedQuery<Long> q = em.createQuery("SELECT COUNT(u) FROM UserEntry u WHERE :userRole IN (u.userRoles)", Long.class);
        q.setParameter("userRole", UserRole.Admin);
        return q.getSingleResult().intValue();
    }
}
