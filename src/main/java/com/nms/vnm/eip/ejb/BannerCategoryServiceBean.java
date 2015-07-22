/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.service.entity.BannerCategoryService;
import javax.ejb.Stateless;

@Stateless
public class BannerCategoryServiceBean extends AbstractFacadeBean<BannerCategory> implements BannerCategoryService {

    private static final long serialVersionUID = -2658343686363511314L;

    public BannerCategoryServiceBean() {
        super(BannerCategory.class);
    }

}
