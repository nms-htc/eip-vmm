/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.MusicCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("musicCatConverter")
public class MusicCatConverter extends AbstractEntityConverter<MusicCategory> {

    @EJB
    private MusicCategoryService musicCatService;

    @Override
    protected BaseService<MusicCategory> getBaseService() {
        return musicCatService;
    }
}
