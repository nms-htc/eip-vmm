/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.BannerCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("bannerCatConverter")
public class BannerCatConverter extends AbstractEntityConverter<BannerCategory> {

    @EJB
    private BannerCategoryService bannerCatService;

    @Override
    protected BaseService<BannerCategory> getBaseService() {
        return bannerCatService;
    }
}
