/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Video;
import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.entity.ProductService;
import com.nms.vnm.eip.service.entity.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoController extends AbstractProductController<Video, VideoCategory> {

    private static final long serialVersionUID = 4995054396472247002L;

    @EJB
    private VideoService videoService;

    @Override
    protected ProductService<Video, VideoCategory> getProductService() {
        return videoService;
    }

}
