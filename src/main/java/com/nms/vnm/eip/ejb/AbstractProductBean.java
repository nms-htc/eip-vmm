/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.BaseEntity_;
import com.nms.vnm.eip.entity.Category;
import com.nms.vnm.eip.entity.Product;
import com.nms.vnm.eip.entity.Product_;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.ProductService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractProductBean<C extends Category, P extends Product> extends AbstractFacadeBean<P> implements ProductService<P, C> {

 private static final long serialVersionUID = -2603765016508854535L;

 public AbstractProductBean(Class<P> entityClass) {
  super(entityClass);
 }

 @Override
 public List<P> findByCat(int start, int range, C category, String orderField, boolean asc) {
  CriteriaBuilder cb = em.getCriteriaBuilder();
  CriteriaQuery<P> cq = cb.createQuery(entityClass);
  Root<P> root = cq.from(entityClass);
  cq.select(root);

  if (category != null) {
   cq.where(cb.equal(root.get(Product_.category), (Category) category));
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
 public List<P> findExcludeCurrent(int start, int range, P product, MobileChecker mobileChecker) {
  if (product != null) {
   C category = (C) product.getCategory();
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<P> cq = cb.createQuery(entityClass);
   Root<P> root = cq.from(entityClass);

   List<Predicate> predicates = buildPredicates(cb, root, category, null);
   if (mobileChecker != null) {
    List<Predicate> mobilePredicates = buildPredicates(cb, root, mobileChecker);
    predicates.addAll(mobilePredicates);
   }
   // exclude current product.
   predicates.add(cb.notEqual(root.get(BaseEntity_.id), ((Product) product).getId()));

   if (!predicates.isEmpty()) {
    cq.where(predicates.toArray(new Predicate[]{}));
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

  cq.where(predicates.toArray(new Predicate[]{}));

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

  cq.where(predicates.toArray(new Predicate[]{}));

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
 public List<P> getNoFrees(int start, int range, C category, String orderField, boolean asc) {
  CriteriaBuilder cb = em.getCriteriaBuilder();
  CriteriaQuery<P> cq = cb.createQuery(entityClass);
  Root<P> root = cq.from(entityClass);
  cq.select(root);

  List<Predicate> predicates = new ArrayList<>();
  predicates.add(cb.gt(root.get(Product_.price), 0));
  if (category != null) {
   predicates.add(cb.equal(root.get(Product_.category), category));
  }

  cq.where(predicates.toArray(new Predicate[]{}));

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
  System.out.println("log:::::start");
  List<P> p = q.getResultList();
  System.out.println("log:::::end");
  return p;
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

  cq.where(predicates.toArray(new Predicate[]{}));

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

  cq.where(predicates.toArray(new Predicate[]{}));

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

 @Override
 public void increaseViewCount(P product) {
  int count = product.getViewCount() + 1;
  product.setViewCount(count);
  em.merge(product);
 }

 @Override
 public void increateDownloadCount(P product) {
  int count = product.getDownloadCount() + 1;
  product.setDownloadCount(count);
  em.merge(product);
 }

 @Override
 protected void onBeforePersist(P entity) {
  super.onBeforePersist(entity);
  Product testEntity = null;
  try {
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<Product> cq = cb.createQuery(Product.class);
   Root<Product> root = cq.from(Product.class);
   cq.select(root);
   cq.where(cb.equal(root.get(Product_.code), entity.getCode()));
   TypedQuery<Product> q = em.createQuery(cq);
   testEntity = q.getSingleResult();
  } catch (Exception e) {
   // valid ok?
  }
  if (testEntity != null) {
   throw new EJBException("product.code.error.duplicate");
  }
 }

 @Override
 public List<P> search(String keywords, C category, MobileChecker mobileChecker,
         String orderField, boolean asc, int start, int range) {
  CriteriaBuilder cb = em.getCriteriaBuilder();
  CriteriaQuery<P> cq = cb.createQuery(entityClass);
  Root<P> root = cq.from(entityClass);
  cq.select(root);

  List<Predicate> predicates = buildPredicates(cb, root, category, keywords);
  if (mobileChecker != null) {
   List<Predicate> mobilePredicates = buildPredicates(cb, root, mobileChecker);
   predicates.addAll(mobilePredicates);
  }

  if (!predicates.isEmpty()) {
   cq.where(predicates.toArray(new Predicate[]{}));
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
 public int count(String keywords, C category, MobileChecker mobileChecker) {
  CriteriaBuilder cb = em.getCriteriaBuilder();
  CriteriaQuery<Long> cq = cb.createQuery(Long.class);
  Root<P> root = cq.from(entityClass);
  cq.select(cb.count(root));

  List<Predicate> predicates = buildPredicates(cb, root, category, keywords);
  if (mobileChecker != null) {
   List<Predicate> mobilePredicates = buildPredicates(cb, root, mobileChecker);
   predicates.addAll(mobilePredicates);
  }
  if (!predicates.isEmpty()) {
   cq.where(predicates.toArray(new Predicate[]{}));
  }

  TypedQuery<Long> q = em.createQuery(cq);
  return q.getSingleResult().intValue();
 }

 /**
  * Build predicates for search
  *
  * @param cb
  * @param root
  * @param category
  * @param keywords
  * @return
  */
 protected List<Predicate> buildPredicates(CriteriaBuilder cb, Root<P> root, C category, String keywords) {
  List<Predicate> predicates = new LinkedList<>();

  predicates.add(cb.isTrue(root.get(Product_.enable)));

  if (category != null) {
   predicates.add(cb.equal(root.get(Product_.category), category));
  }

  if (keywords != null && !keywords.trim().isEmpty()) {
   predicates.add(cb.like(cb.upper(root.get(Product_.title)), '%' + keywords.trim().toUpperCase() + '%'));
  }

  return predicates;
 }

 /**
  * Build predicats for search on Wap
  *
  * @param cd
  * @param root
  * @param mobileChecker
  * @return
  */
 protected abstract List<Predicate> buildPredicates(CriteriaBuilder cd, Root<P> root, MobileChecker mobileChecker);
}
