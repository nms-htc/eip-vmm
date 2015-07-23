/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Banner;
import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.service.entity.BannerCategoryService;
import com.nms.vnm.eip.service.entity.BannerService;
import com.nms.vnm.eip.service.entity.ProductService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BannerController extends AbstractProductController<Banner, BannerCategory> {

 private static final long serialVersionUID = 6716553296353367211L;
 private static final long TOP_BAN_ID = 2651;
 private static final long MID_BAN_ID = 2652;
 private static final long BOTTOM_BAN_ID = 2653;
 private static final long FEATURE_BAN_ID = 2901;
 protected List<Banner> topBaner; //4 banner
 protected List<Banner> middleBanner;//6 banner
 protected List<Banner> footerBanner;//6 banner
 protected List<Banner> featureBanner;//2 banner
 @EJB
 private BannerService bannerService;
 @EJB
 private BannerCategoryService catService;

 @Override
 protected ProductService<Banner, BannerCategory> getProductService() {
  return bannerService;
 }
 public List<Banner> getFeatureBanner() {
  if (featureBanner == null) {
   BannerCategory cat = catService.find(FEATURE_BAN_ID);
   featureBanner = bannerService.search(null, cat, null, "createdDate", false, 0, 2);
  }
  return featureBanner;
 }

 public void setFeatureBanner(List<Banner> featureBanner) {
  this.featureBanner = featureBanner;
 }

 public List<Banner> getTopBaner() {
  if (topBaner == null) {
   BannerCategory cat = catService.find(TOP_BAN_ID);
   topBaner = bannerService.search(null, cat, null, "createdDate", false, 0, 4);
  }
  return topBaner;
 }

 public void setTopBaner(List<Banner> topBaner) {
  this.topBaner = topBaner;
 }

 public List<Banner> getMiddleBanner() {
  if (middleBanner == null) {
   BannerCategory cat = catService.find(MID_BAN_ID);
   middleBanner = bannerService.search(null, cat, null, "createdDate", false, 0, 6);
  }
  return middleBanner;
 }

 public void setMiddleBanner(List<Banner> middleBanner) {
  this.middleBanner = middleBanner;
 }

 public List<Banner> getFooterBanner() {
  if (footerBanner == null) {
   BannerCategory cat = catService.find(BOTTOM_BAN_ID);
   footerBanner = bannerService.search(null, cat, null, "createdDate", false, 0, 6);
  }
  return footerBanner;
 }

 public void setFooterBanner(List<Banner> footerBanner) {
  this.footerBanner = footerBanner;
 }

 public BannerService getBannerService() {
  return bannerService;
 }

 public void setBannerService(BannerService bannerService) {
  this.bannerService = bannerService;
 }
}
