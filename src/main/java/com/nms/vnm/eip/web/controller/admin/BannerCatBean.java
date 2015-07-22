/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.BannerCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BannerCatBean extends AbstractManagedBean<BannerCategory> {

    private static final long serialVersionUID = -3400045537994131927L;
    @EJB
    private BannerCategoryService bannerCatService;

    @Override
    protected BannerCategory initEntity() {
        return new BannerCategory();
    }

    @Override
    protected BaseService<BannerCategory> getBaseService() {
        return bannerCatService;
    }

}
