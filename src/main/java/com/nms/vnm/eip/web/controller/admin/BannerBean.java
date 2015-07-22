/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.Banner;
import com.nms.vnm.eip.service.entity.BannerService;
import com.nms.vnm.eip.service.entity.BaseService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BannerBean extends AbstractProductBean<Banner> {

 private static final long serialVersionUID = 2219397982611609176L;
 @EJB
 private BannerService banenrService;

 @Override
 protected Banner initEntity() {
  return new Banner();
 }

 @Override
 protected BaseService<Banner> getBaseService() {
  return banenrService;
 }

}
