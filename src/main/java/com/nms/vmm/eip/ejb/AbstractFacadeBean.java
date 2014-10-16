/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.service.entity.BaseService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacadeBean<T> implements BaseService<T> {

    private static final long serialVersionUID = -3193947671333109472L;

    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> entityClass;

    public AbstractFacadeBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public int countAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    /* Callback method */
    protected void onBeforePersist(T entity) {
    }

    /* Callback method */
    protected void onAfterPersist(T entity) {
        
    }

    @Override
    public T persist(T entity) {
        onBeforePersist(entity);
        em.persist(entity);
        em.flush();
        onAfterPersist(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeUpdate(T entity) {
    }

    /* Callback method */
    protected void onAfterUpdate(T entity) {
    }

    @Override
    public T update(T entity) {
        onBeforeUpdate(entity);
        em.merge(entity);
        onAfterUpdate(entity);
        return entity;
    }

    /* Callback method */
    protected void onBeforeRemove(T entity) {
    }

    /* Callback method */
    protected void onAfterRemove(T entity) {
    }

    @Override
    public void remove(T entity) {
        onBeforeRemove(entity);
        em.remove(em.merge(entity));
        onAfterRemove(entity);
    }
}
