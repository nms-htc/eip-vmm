/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.entity.Category;
import com.nms.vnm.eip.entity.Product;
import com.nms.vnm.eip.service.ChargingService;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.BannerCategoryService;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.ProductService;
import com.nms.vnm.eip.web.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractProductController<T extends Product, C extends Category> implements Serializable {

 private static final long serialVersionUID = -1246504599210472022L;
 protected static final Logger LOGGER = Logger.getLogger("AbstractProductController");

 protected T current;
 protected C category;
 protected int page = 0;
 protected boolean hasNext = false;
 protected boolean hasPreview = false;
 protected int count = 0;
 protected String orderField = "createdDate";

 @Inject
 protected ChargingService chargingService;
 @Inject
 protected MobileChecker mobileChecker;

 private final boolean isCheckMobile = true; //bat tat mobile checker
 protected List<T> model;

 protected List<T> listByCat;
 protected List<T> listExcludeCurrent;
 private List<Integer> listPagging;

 public void chargingProduct() {
  if (mobileChecker.isVnmSubsriber()) {
   try {
    FacesMessage message = chargingService.chargeProduct(current);
    if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO)) {
     getProductService().increateDownloadCount(current);
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
   } catch (Exception e) {
    MessageUtil.addGlobalWarnMessage("charging-system-overloading");
    LOGGER.log(Level.SEVERE, "charing-service-error", e);
   }
  } else {
   MessageUtil.addGlobalWarnMessage("vnm-subscriber-not-detech");
  }
 }

 public void chargingProduct(T product) {
  if (mobileChecker.isVnmSubsriber()) {
   try {
    FacesMessage message = chargingService.chargeProduct(product);
    if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO)) {
     getProductService().increateDownloadCount(product);
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
   } catch (Exception e) {
    MessageUtil.addGlobalWarnMessage("charging-system-overloading");
    LOGGER.log(Level.SEVERE, "charing-service-error", e);
   }
  } else {
   MessageUtil.addGlobalWarnMessage("vnm-subscriber-not-detech");
  }
 }

 public C getCategory() {
  return category;
 }

 public void setCategory(C category) {
  this.category = category;
 }

 protected List<T> hots;
 protected List<T> tops;
 protected List<T> news;
 protected List<T> frees;
 protected List<T> nofrees;
 protected List<T> promos;

 protected List<T> hots20;
 protected List<T> tops20;
 protected List<T> news20;

 private String keyword;

 public T getCurrent() {
  return current;
 }

 public void setCurrent(T current) {
  this.current = current;
 }

 public List<T> getListByCat() {
  return listByCat;
 }

 public List<T> getListExcludeCurrent() {
  return getProductService().findExcludeCurrent(0, 4, current, isCheckMobile ? mobileChecker : null);
 }

 public List<T> getHots() {
  if (hots == null) {
   return hots = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, 0, 10);
  }
  return hots;
 }

 public List<T> getHots20() {
  if (hots20 == null) {
   return hots20 = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, 10, 10);
  }
  return hots20;
 }

 public List<T> getHots(int start, int range) {
  return getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, start, range);
 }

 public List<T> getTops() {
  if (tops == null) {
   return tops = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, 0, 10);
  }
  return tops;
 }

 public List<T> getTops20() {
  if (tops20 == null) {
   return tops20 = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, 10, 10);
  }
  return tops20;
 }

 public List<T> getTops(int start, int range) {
  return getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "downloadCount", false, start, range);
 }

 public List<T> getNews() {
  if (news == null) {
   return news = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "createdDate", false, 0, 10);
  }
  return news;
 }

 public List<T> getNews20() {
  if (news20 == null) {
   return news20 = getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "createdDate", false, 10, 10);
  }
  return news20;
 }

 public List<T> getList5ByCat(Category inputCat) {
  return getProductService().search(null, (C) inputCat, isCheckMobile ? mobileChecker : null, "createdDate", false, 0, 5);
 }

 public List<T> getNews(int start, int range) {
  return getProductService().search(null, category, isCheckMobile ? mobileChecker : null, "createdDate", false, start, range);
 }

 public List<T> getFrees() {
  if (frees == null) {
   return frees = getProductService().getFrees(0, 10, category, "createdDate", false);
  }
  return frees;
 }

 public List<T> getNoFrees() {
  if (nofrees == null) {
   return nofrees = getProductService().getNoFrees(0, 10, category, "createdDate", false);
  }
  return nofrees;
 }

 public List<T> getPromos() {
  if (promos == null) {
   return promos = getProductService().getPromotions(0, 10, category, "createdDate", false);
  }
  return promos;
 }

 public List<T> getModel() {
  return model;
 }

 public void setPage(int page) {
  this.page = page;
 }

 public int getPage() {
  return page;
 }

 public void setOrderField(String orderField) {
  this.orderField = orderField;
 }

 public List<T> randomList(List<T> inputList) {
  Random rand = new Random();
  int n = rand.nextInt(50) + 1;
  if ((n % 2) == 0) {
   Collections.reverse(inputList);
  }
  return inputList;
 }

 public void initData() {
  int numberPerPage = 10;
  if (current != null) {
   // increase view count
   getProductService().increaseViewCount(current);
   model = getProductService().findExcludeCurrent(page * numberPerPage, numberPerPage, current, isCheckMobile ? mobileChecker : null);
   category = (C) current.getCategory();
   count = getProductService().count(null, category, isCheckMobile ? mobileChecker : null) - 1;
  } else if (category != null) {
   model = getProductService().findByCat(page * numberPerPage, numberPerPage, category, orderField, false);
   count = getProductService().count(null, category, isCheckMobile ? mobileChecker : null);
  } else if (keyword != null) {
   model = getProductService().search(keyword, null, isCheckMobile ? mobileChecker : null, "createdDate", false, page * numberPerPage, numberPerPage);
   count = getProductService().count(keyword, null, isCheckMobile ? mobileChecker : null);
  }
  if (current != null || category != null || null != keyword) {
   if ((page + 1) * numberPerPage < count) {
    hasNext = true;
   }
   if (page > 0) {
    hasPreview = true;
   }
  }
  /*page number*/
  if (page > 1) {
   getListPagging().add(page - 2);
  }
  if (page > 0) {
   getListPagging().add(page - 1);
  }
  getListPagging().add(page);
  if ((page + 1) * numberPerPage < count) {
   getListPagging().add(page + 1);
  }
  if ((page + 2) * numberPerPage < count) {
   getListPagging().add(page + 2);
  }
 }

 public boolean isHasNext() {
  return hasNext;
 }

 public void setHasNext(boolean hasNext) {
  this.hasNext = hasNext;
 }

 public boolean isHasPreview() {
  return hasPreview;
 }

 public void setHasPreview(boolean hasPreview) {
  this.hasPreview = hasPreview;
 }

 public int getCount() {
  return count;
 }

 public void setCount(int count) {
  this.count = count;
 }

 protected abstract ProductService<T, C> getProductService();

 /**
  * @return the listPagging
  */
 public List<Integer> getListPagging() {
  if (listPagging == null) {
   listPagging = new ArrayList<>();
  }
  return listPagging;
 }

 /**
  * @param listPagging the listPagging to set
  */
 public void setListPagging(List<Integer> listPagging) {
  this.listPagging = listPagging;
 }

 /**
  * @return the keyword
  */
 public String getKeyword() {
  return keyword;
 }

 /**
  * @param keyword the keyword to set
  */
 public void setKeyword(String keyword) {
  this.keyword = keyword;
 }

}
