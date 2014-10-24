/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.PictureCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PictureCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("pictureCatConverter")
public class PictureCatConverter extends AbstractEntityConverter<PictureCategory> {

    @EJB
    private PictureCategoryService pictureCatService;

    @Override
    protected BaseService<PictureCategory> getBaseService() {
        return pictureCatService;
    }
}
