/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.convertor;

import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.MusicCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = MusicCategory.class)
public class MusicCatConvertor extends AbstractEntityConvertor<MusicCategory> {

    @EJB
    private MusicCategoryService musicCatService;

    @Override
    protected BaseService<MusicCategory> getBaseService() {
        return musicCatService;
    }
}
