/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Category;
import com.nms.vnm.eip.entity.Product;
import com.nms.vnm.eip.entity.Product_;
import com.nms.vnm.eip.service.entity.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstratProductBean<C extends Category, P extends Product<C>> extends AbstractFacadeBean<P> implements ProductService<P, C> {

    private static final long serialVersionUID = -2603765016508854535L;

    public AbstratProductBean(Class<P> entityClass) {
        super(entityClass);
    }

    @Override
    public List<P> findByCat(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);

        if (category != null) {
            cq.where(cb.equal(root.get(Product_.category), (Category)category));
        }

        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }

        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }

    @Override
    public List<P> findExcludeCurrent(int start, int range, P product) {
        if (product != null) {
            Category category = product.getCategory();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<P> cq = cb.createQuery(entityClass);
            Root<P> root = cq.from(entityClass);
            if (category != null) {
                C cat = (C) category;
                cq.where(new Predicate[] {
                    cb.equal(root.get(Product_.category), cat),
                    cb.notEqual(root.get(Product_.id), ((Product)product).getId())
                });
            }
            TypedQuery<P> q = em.createQuery(cq);
            q.setFirstResult(start);
            q.setMaxResults(range);
            return q.getResultList();
        }
        return null;
    }

    @Override
    public List<P> getPromotions(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.greaterThan(root.get(Product_.promoPrice), Double.valueOf(0)));
        if (category != null) {
            predicates.add(cb.equal(root.get(Product_.category), category));
        }

        cq.where(predicates.toArray(new Predicate[] {}));

        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }

        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }

    @Override
    public List<P> getFrees(int start, int range, C category, String orderField, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(entityClass);
        Root<P> root = cq.from(entityClass);
        cq.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Product_.price), 0));
        if (category != null) {
            predicates.add(cb.equal(root.get(Product_.category), category));
        }

        cq.where(predicates.toArray(new Predicate[] {}));

        if (orderField != null && !orderField.trim().isEmpty()) {
            if (asc) {
                cq.orderBy(cb.asc(root.get(orderField)));
            } else {
                cq.orderBy(cb.desc(root.get(orderField)));
            }
        }

        TypedQuery<P> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(range);
        return q.getResultList();
    }

    @Override
    public int countFrees(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Product_.price), 0));
        if (category != null) {
            predicates.add(cb.equal(root.get(Product_.category), category));
        }

        cq.where(predicates.toArray(new Predicate[] {}));

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public int countPromotions(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.greaterThan(root.get(Product_.promoPrice), Double.valueOf(0)));
        if (category != null) {
            predicates.add(cb.equal(root.get(Product_.category), category));
        }

        cq.where(predicates.toArray(new Predicate[] {}));

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public int countByCat(C category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(entityClass);
        cq.select(cb.count(root));

        if (category != null) {
            cq.where(cb.equal(root.get(Product_.category), category));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

}
