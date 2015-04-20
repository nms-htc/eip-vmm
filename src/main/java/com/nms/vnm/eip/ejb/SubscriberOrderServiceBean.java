/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.SubscriberOrder;
import com.nms.vnm.eip.entity.SubscriberOrder_;
import com.nms.vnm.eip.service.entity.SubscriberOrderService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author cuongnt
 */
@Stateless
public class SubscriberOrderServiceBean implements SubscriberOrderService {

    private static final long serialVersionUID = 3222627359810891642L;

    @PersistenceContext
    private EntityManager em;

    @Override
    public SubscriberOrder find(Object id) {
        return em.find(SubscriberOrder.class, id);
    }

    @Override
    public List<SubscriberOrder> findAll() {
        TypedQuery<SubscriberOrder> q = em.createQuery("SELECT subOrder FROM SubscriberOrder subOrder", SubscriberOrder.class);
        return q.getResultList();
    }

    @Override
    public int countAll() {
        TypedQuery<Long> q = em.createQuery("SELECT COUNT(subOrder.orderid) FROM SubscriberOrder subOrder", Long.class);
        return q.getFirstResult();
    }

    @Override
    public SubscriberOrder persist(SubscriberOrder entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public SubscriberOrder update(SubscriberOrder entity) {
        return em.merge(entity);
    }

    @Override
    public void remove(SubscriberOrder entity) {
        em.remove(entity);
    }

    @Override
    public List<SubscriberOrder> search(int start, int range, String sortField, boolean asc, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SubscriberOrder> cq = cb.createQuery(SubscriberOrder.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(root);

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        Order[] orders = buildOrder(sortField, asc, cb, root);

        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<SubscriberOrder> q = em.createQuery(cq);

        q.setFirstResult(start);
        q.setMaxResults(range);

        return q.getResultList();
    }

    @Override
    public int count(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    @Override
    public List<SubscriberOrder> search(String cpCode, Date starOrderDate, Date endOrderDate, int start, int range, String sortField, boolean asc, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SubscriberOrder> cq = cb.createQuery(SubscriberOrder.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(root);

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (cpCode != null) {
            predicates.add(cb.like(root.get(SubscriberOrder_.contentcode), cpCode));
        }
        
        if (starOrderDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(SubscriberOrder_.orderdate), starOrderDate));
        }
        
        if (endOrderDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(SubscriberOrder_.orderdate), endOrderDate));
        }
        
        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        Order[] orders = buildOrder(sortField, asc, cb, root);

        if (orders != null && orders.length > 0) {
            cq.orderBy(orders);
        }

        TypedQuery<SubscriberOrder> q = em.createQuery(cq);

        q.setFirstResult(start);
        q.setMaxResults(range);

        return q.getResultList();
    }

    @Override
    public int count(String cpCode, Date starOrderDate, Date endOrderDate, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (cpCode != null) {
            predicates.add(cb.like(root.get(SubscriberOrder_.contentcode), cpCode));
        }
        
        if (starOrderDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(SubscriberOrder_.orderdate), starOrderDate));
        }
        
        if (endOrderDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(SubscriberOrder_.orderdate), endOrderDate));
        }

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    protected List<Predicate> buildConditions(Map<String, Object> filters, Root<SubscriberOrder> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // process filter
        filters.entrySet().stream().map((entry) -> buildCondition(entry, root, cb))
                .filter((predicate) -> (predicate != null))
                .forEach((predicate) -> {
                    predicates.add(predicate);
                });

        // Get order successfully charging
        predicates.add(cb.equal(root.get(SubscriberOrder_.status), 0));

        return predicates;
    }

    protected Predicate buildCondition(Map.Entry<String, Object> entry, Root<SubscriberOrder> root, CriteriaBuilder cb) {
        return cb.equal(root.get(entry.getKey()), entry.getValue());
    }

    protected Order[] buildOrder(String sortField, boolean asc, CriteriaBuilder cb, Root<SubscriberOrder> root) {
        List<Order> orders = new ArrayList<>();

        if (sortField == null || sortField.isEmpty()) {
            orders.add(cb.desc(root.get(SubscriberOrder_.createdate)));
        } else {
            if (asc) {
                orders.add(cb.asc(root.get(sortField)));
            } else {
                orders.add(cb.desc(root.get(sortField)));
            }
        }
        return orders.toArray(new Order[]{});
    }

    @Override
    public double calculateTotalAmount(String cpCode, Date starOrderDate, Date endOrderDate, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(cb.sum(root.get(SubscriberOrder_.amount)));

        List<Predicate> predicates = buildConditions(filters, root, cb);

        if (cpCode != null) {
            predicates.add(cb.like(root.get(SubscriberOrder_.contentcode), cpCode));
        }
        
        if (starOrderDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(SubscriberOrder_.orderdate), starOrderDate));
        }
        
        if (endOrderDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(SubscriberOrder_.orderdate), endOrderDate));
        }

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<BigDecimal> q = em.createQuery(cq);
        return q.getSingleResult().doubleValue();
    }
}
