/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.PictureCategory;
import com.nms.vmm.eip.service.entity.PictureCategoryService;
import javax.ejb.Stateless;

@Stateless
public class PictureCategoryServiceBean extends AbstractFacadeBean<PictureCategory> implements PictureCategoryService {

    private static final long serialVersionUID = -2658343686363511314L;

    public PictureCategoryServiceBean() {
        super(PictureCategory.class);
    }

}
