/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.service.entity.UserService;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserServiceBean extends AbstractFacadeBean<User> implements UserService {

    private static final long serialVersionUID = 1508900539829327598L;
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    public UserServiceBean() {
        super(User.class);
    }

    @Override
    public boolean hasAdminUser() {
        TypedQuery<Long> q = em.createQuery("SELECT COUNT(u) FROM User u WHERE :adminRole IN (u.groups)", Long.class);
        q.setParameter("adminRole", User.Group.Admin);
        int count = q.getSingleResult().intValue();
        return count > 0;
    }

    @Override
    public void updatePassword(User user) {
        user.hashPassword();
        em.merge(user);
    }
    
    
}
