/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.convertor;

import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.VideoCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = VideoCategory.class)
public class VideoCatConvertor extends AbstractEntityConvertor<VideoCategory> {

    @EJB
    private VideoCategoryService videoCatService;

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return videoCatService;
    }

}
