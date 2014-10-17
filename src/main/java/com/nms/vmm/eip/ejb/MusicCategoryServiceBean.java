/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.MusicCategory;
import com.nms.vmm.eip.service.entity.MusicCategoryService;
import javax.ejb.Stateless;

@Stateless
public class MusicCategoryServiceBean extends AbstractFacadeBean<MusicCategory> implements MusicCategoryService {

    private static final long serialVersionUID = 8498251689261582829L;

    public MusicCategoryServiceBean() {
        super(MusicCategory.class);
    }

}
