/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.entity.User_;
import com.nms.vmm.eip.exception.AppException;
import com.nms.vmm.eip.exception.ErrorInfo;
import com.nms.vmm.eip.service.entity.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class UserServiceBean extends AbstractFacadeBean<User> implements UserService {

    private static final long serialVersionUID = 1508900539829327598L;
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    public UserServiceBean() {
        super(User.class);
    }

    @Override
    protected void onBeforePersist(User entity) {
        super.onBeforePersist(entity);

        User check;
        // check code
        check = findByCode(entity.getCode());
        if (check != null) {
            throw new EJBException("user-code-has-bean-exist");
        }
        // check username
        check = findByUsername(entity.getUsername());
        if (check != null) {
            throw new EJBException("user-username-has-been-exist");
        }
        // check email
        check = findByEmail(entity.getEmail());
        if (check != null) {
            throw new EJBException("user-email-has-bean-exist");
        }
    }

    @Override
    protected void onBeforeUpdate(User entity) {
        super.onBeforeUpdate(entity);
        User check;
        // check username
        check = findByUsername(entity.getUsername());
        if (check != null && !check.equals(entity)) {
            throw new EJBException("user-username-has-been-exist");
        }
        // check code
        check = findByCode(entity.getCode());
        if (check != null && !check.equals(entity)) {
            throw new EJBException("user-code-has-bean-exist");
        }
        // check email
        check = findByEmail(entity.getEmail());
        if (check != null && !check.equals(entity)) {
            throw new EJBException("user-email-has-bean-exist");
        }
    }

    @Override
    public boolean hasAdminUser() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(cb.count(root));
        cq.where(cb.isMember(User.Group.Admin, root.get(User_.groups)));
        TypedQuery<Long> q = em.createQuery(cq);
        int count = q.getSingleResult().intValue();
        return count > 0;
    }

    @Override
    public void updatePassword(User user) {
        user.hashPassword();
        em.merge(user);
    }

    @Override
    public User findByCode(String code) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.code = :code", User.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, e.toString(), e);
            return null;
        } catch (NonUniqueResultException e) {
            throw new AppException(ErrorInfo.UNIQUE_CONSTRAINT_ERROR, "Has more than one records existing with code =" + code, e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, e.toString(), e);
            return null;
        } catch (NonUniqueResultException e) {
            throw new AppException(ErrorInfo.UNIQUE_CONSTRAINT_ERROR, "Has more than one records existing with email =" + email, e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, e.toString(), e);
            return null;
        } catch (NonUniqueResultException e) {
            throw new AppException(ErrorInfo.UNIQUE_CONSTRAINT_ERROR, "Has more than one records existing with username =" + username, e);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            LOG.log(Level.WARNING, e.toString(), e);
            return null;
        } catch (NonUniqueResultException e) {
            throw new AppException(ErrorInfo.UNIQUE_CONSTRAINT_ERROR, "Has more than one records existing with username ="
                    + username + ", password = " + password, e);
        }

    }
}
