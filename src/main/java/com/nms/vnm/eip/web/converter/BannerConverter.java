/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.Banner;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.BannerService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("bannerConverter")
public class BannerConverter extends AbstractEntityConverter<Banner> {

    @EJB
    private BannerService bannerService;

    @Override
    protected BaseService<Banner> getBaseService() {
        return bannerService;
    }

    @Override
    protected Class<Banner> getEntityClass() {
        return Banner.class;
    }

}
