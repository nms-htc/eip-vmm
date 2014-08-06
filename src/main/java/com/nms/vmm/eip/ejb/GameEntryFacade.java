/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Flatform;
import com.nms.vmm.eip.entity.GameCategory;
import com.nms.vmm.eip.entity.GameCategory_;
import com.nms.vmm.eip.entity.GameEntry;
import com.nms.vmm.eip.entity.GameEntry_;
import com.nms.vmm.eip.search.GameEntryCriteria;
import com.nms.vmm.eip.search.OrderType;
import com.nms.vmm.eip.web.util.UserAgentInfo;
import com.nms.vmm.eip.web.util.Validator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cuong
 */
@Stateless
public class GameEntryFacade extends AbstractFacade<GameEntry> implements Serializable {

    private static final long serialVersionUID = 1894346867028333637L;

    @PersistenceContext
    private EntityManager em;

    public List<GameEntry> findByCategoryId(Long categoryId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        cq.select(root);
        cq.where(cb.equal(category.get(GameCategory_.id), categoryId));
        TypedQuery<GameEntry> q = em.createQuery(cq);

        return q.getResultList();
    }

    public List<GameEntry> findByCategory(Long categoryId, UserAgentInfo agentInfo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        List<Predicate> pridicates = new ArrayList<>();
        cq.select(root);
        if (category != null) {
            pridicates.add(cb.equal(category.get(GameCategory_.id), categoryId));
        }
        Predicate p = getDetectMobilePredicate(root, agentInfo, cb);

        if (p != null) {
            pridicates.add(p);
        }

        cq.where(pridicates.toArray(new Predicate[]{}));
        TypedQuery<GameEntry> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<GameEntry> findByCategory(Long categoryId, UserAgentInfo agentInfo, int[] range,
            String orderByField, String orderByType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        List<Predicate> pridicates = new ArrayList<>();
        cq.select(root);

        if (categoryId != null) {
            pridicates.add(cb.equal(category.get(GameCategory_.id), categoryId));
        }
        Predicate p = getDetectMobilePredicate(root, agentInfo, cb);

        if (p != null) {
            pridicates.add(p);
        }

        cq.where(pridicates.toArray(new Predicate[]{}));

        if (orderByField != null && orderByField.trim().length() > 0) {
            if (orderByType != null && orderByType.trim().length() > 0 && orderByType.equals("desc")) {
                cq.orderBy(cb.desc(root.get(orderByField)));
            } else {
                cq.orderBy(cb.asc(root.get(orderByField)));
            }
        }

        TypedQuery<GameEntry> q = em.createQuery(cq);
        if (range != null && range.length == 2) {
            q.setFirstResult(range[0]);
            q.setMaxResults(range[1] - range[0] + 1);
        }
        return q.getResultList();
    }

    public int countByCategory(Long categoryId, UserAgentInfo agentInfo) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        List<Predicate> pridicates = new ArrayList<>();
        cq.select(cb.count(root));

        if (categoryId != null) {
            pridicates.add(cb.equal(category.get(GameCategory_.id), categoryId));
        }
        Predicate p = getDetectMobilePredicate(root, agentInfo, cb);

        if (p != null) {
            pridicates.add(p);
        }

        cq.where(pridicates.toArray(new Predicate[]{}));

        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    public GameEntryFacade() {
        super(GameEntry.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(GameEntry entity, String cpCode) {

        Date now = new Date();
        entity.setCreatedDate(now);
        entity.setModifiedDate(now);
        entity.setCpCode(cpCode);

        super.create(entity);
    }

    public void edit(GameEntry entity, String cpCode) {

        Date now = new Date();
        entity.setModifiedDate(now);
        entity.setCpCode(cpCode);

        super.edit(entity);
    }

    public void create(List<GameEntry> entities, String cpCode) {
        Date now = new Date();
        for (GameEntry entity : entities) {
            entity.setModifiedDate(now);
            entity.setCreatedDate(now);
            entity.setCpCode(cpCode);
            em.persist(entity);
        }
    }

    private Predicate getDetectMobilePredicate(Root<GameEntry> root, UserAgentInfo agentInfo, CriteriaBuilder cb) {
        Predicate predicate = null;

        if (agentInfo != null && agentInfo.isMobileDevice()) {
            if (agentInfo.detectAndroid()) {
                predicate = cb.isMember(Flatform.ANDROID, root.get(GameEntry_.flatforms));
            } else if (agentInfo.detectIos()) {
                predicate = cb.isMember(Flatform.IOS, root.get(GameEntry_.flatforms));
            } else if (agentInfo.detectWindowsMobile()) {
                predicate = cb.isMember(Flatform.WINDOW_PHONE, root.get(GameEntry_.flatforms));
            } else {
                predicate = cb.or(
                        cb.isMember(Flatform.JAVA, root.get(GameEntry_.flatforms)),
                        cb.isMember(Flatform.JAVA, root.get(GameEntry_.flatforms)));

            }
        }
        return predicate;
    }

    public List<GameEntry> search(GameEntryCriteria criteria, int start, int range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        cq.select(root);

        List<Predicate> predicates = buildPredicate(cb, root, category, criteria);

        if (predicates != null && predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<GameEntry> q = em.createQuery(cq);
        if (start >= 0 && range > 0) {
            q.setFirstResult(start);
            q.setMaxResults(range);
        }
        return q.getResultList();
    }

    public int count(GameEntryCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> category = root.join(GameEntry_.category, JoinType.LEFT);
        cq.select(cb.count(root));

        List<Predicate> predicates = buildPredicate(cb, root, category, criteria);

        if (predicates != null && predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        Long result = q.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

    /**
     * For webservice
     *
     * @param categoryId
     * @param keywords
     * @param orderType
     * @param start
     * @param range
     * @return
     */
    public List<GameEntry> search(Long categoryId, String keywords, OrderType orderType, int start,
            int range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> categoryJoin = root.join(GameEntry_.category, JoinType.LEFT);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        // check categoryId
        if (categoryId != null && categoryId > 0) {
            predicates.add(cb.equal(categoryJoin.get(GameCategory_.id), categoryId));
        }

        // check keywords
        if (Validator.isNotNull(keywords)) {
            keywords = "%" + keywords.toUpperCase() + "%";
            Predicate titlePredicate = cb.like(cb.upper(root.get(GameEntry_.title)), keywords);
            Predicate descriptionPredicate = cb.like(cb.upper(root.get(GameEntry_.description)), keywords);
            predicates.add(cb.or(new Predicate[]{
                titlePredicate,
                descriptionPredicate
            }));
        }

        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        if (orderType != null) {
            switch (orderType) {
                case TOP_DOWNLOAD:
                    cq.orderBy(cb.desc(root.get(GameEntry_.downloadCount)));
                    break;
                case TOP_HOT:
                    cq.orderBy(cb.desc(root.get(GameEntry_.hot)));
                    break;
                case TOP_NEW:
                    cq.orderBy(cb.desc(root.get(GameEntry_.createdDate)));
                    break;
            }
        }

        // Create TypedQuery
        TypedQuery<GameEntry> q = em.createQuery(cq);

        // validate start and rangle
        if (start > 0 && range > 0) {
            q.setFirstResult(start);
            q.setMaxResults(range);
        }

        return q.getResultList();
    }

    /**
     * For webservices.
     *
     * @param categoryId
     * @param keywords
     * @return
     */
    public int count(Long categoryId, String keywords) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        Join<GameEntry, GameCategory> categoryJoin = root.join(GameEntry_.category, JoinType.LEFT);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();

        // check categoryId
        if (categoryId > 0) {
            predicates.add(cb.equal(categoryJoin.get(GameCategory_.id), categoryId));
        }

        // check keywords
        if (Validator.isNotNull(keywords)) {
            keywords = "%" + keywords.toUpperCase() + "%";
            Predicate titlePredicate = cb.like(cb.upper(root.get(GameEntry_.title)), keywords);
            Predicate descriptionPredicate = cb.like(cb.upper(root.get(GameEntry_.description)), keywords);
            predicates.add(cb.or(new Predicate[]{
                titlePredicate,
                descriptionPredicate
            }));
        }

        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);
        Long result = q.getSingleResult();

        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

    public List<GameEntry> search(String keywords, UserAgentInfo agentInfo, int start, int range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GameEntry> cq = cb.createQuery(GameEntry.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        // check keywords
        if (Validator.isNotNull(keywords)) {
            keywords = "%" + keywords.toUpperCase() + "%";
            Predicate titlePredicate = cb.like(cb.upper(root.get(GameEntry_.title)), keywords);
            Predicate descriptionPredicate = cb.like(cb.upper(root.get(GameEntry_.description)), keywords);
            predicates.add(cb.or(new Predicate[]{
                titlePredicate,
                descriptionPredicate
            }));
        }

        Predicate p = getDetectMobilePredicate(root, agentInfo, cb);

        if (p != null) {
            predicates.add(p);
        }

        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        // order
        cq.orderBy(cb.asc(root.get(GameEntry_.title)));

        // Create TypedQuery
        TypedQuery<GameEntry> q = em.createQuery(cq);

        // validate start and rangle
        if (start >= 0 && range >= 0) {
            q.setFirstResult(start);
            q.setMaxResults(range);
        }

        return q.getResultList();
    }

    public int count(String keywords, UserAgentInfo agentInfo) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GameEntry> root = cq.from(GameEntry.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();

        // check keywords
        if (Validator.isNotNull(keywords)) {
            keywords = "%" + keywords.toUpperCase() + "%";
            Predicate titlePredicate = cb.like(cb.upper(root.get(GameEntry_.title)), keywords);
            Predicate descriptionPredicate = cb.like(cb.upper(root.get(GameEntry_.description)), keywords);
            predicates.add(cb.or(new Predicate[]{
                titlePredicate,
                descriptionPredicate
            }));
        }

        Predicate p = getDetectMobilePredicate(root, agentInfo, cb);

        if (p != null) {
            predicates.add(p);
        }

        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        // Create TypedQuery
        TypedQuery<Long> q = em.createQuery(cq);

        Long result = q.getSingleResult();
        
        if (result != null) {
            return result.intValue();
        }
       
        return 0;
    }

    private List<Predicate> buildPredicate(CriteriaBuilder cb, Root<GameEntry> root, Join<GameEntry, GameCategory> categoryJoin,
            GameEntryCriteria criteria) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getId() != null) {
            predicates.add(cb.equal(root.get(GameEntry_.id), criteria.getId()));
        }

        if (Validator.isNotNull(criteria.getTitle())) {
            predicates.add(cb.like(cb.upper(root.get(GameEntry_.title)), "%" + criteria.getTitle().toUpperCase() + "%"));
        }

        if (Validator.isNotNull(criteria.getDescription())) {
            predicates.add(cb.like(cb.upper(root.get(GameEntry_.description)), "%" + criteria.getDescription().toUpperCase() + "%"));
        }

        if (Validator.isNotNull(criteria.getCode())) {
            predicates.add(cb.like(cb.upper(root.get(GameEntry_.code)), "%" + criteria.getCode().toUpperCase() + "%"));
        }

        if (Validator.isNotNull(criteria.getCpCode())) {
            predicates.add(cb.equal(root.get(GameEntry_.cpCode), criteria.getCpCode()));
        }

        // price
        if (Validator.isNotNull(criteria.getPrice())) {
            predicates.add(cb.equal(root.get(GameEntry_.price), criteria.getPrice()));
        }

        //TODO: Update is code late
        // maxPrice
        // minPrice
        // maxCreateDate
        // minCreateDate
        return predicates;
    }
}
