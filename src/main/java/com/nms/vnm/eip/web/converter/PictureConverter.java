/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.Picture;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PictureService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("pictureConverter")
public class PictureConverter extends AbstractEntityConverter<Picture> {

    @EJB
    private PictureService pictureService;

    @Override
    protected BaseService<Picture> getBaseService() {
        return pictureService;
    }

    @Override
    protected Class<Picture> getEntityClass() {
        return Picture.class;
    }
    
}
