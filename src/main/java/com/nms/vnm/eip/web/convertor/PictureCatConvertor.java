/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.convertor;

import com.nms.vnm.eip.entity.PictureCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PictureCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = PictureCategory.class)
public class PictureCatConvertor extends AbstractEntityConvertor<PictureCategory> {

    @EJB
    private PictureCategoryService pictureCatService;

    @Override
    protected BaseService<PictureCategory> getBaseService() {
        return pictureCatService;
    }
}
