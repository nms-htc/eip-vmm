/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.VideoCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoCatBean extends AbstractManagedBean<VideoCategory> {

    private static final long serialVersionUID = -4446171873229250459L;
    @EJB
    private VideoCategoryService videoCatService;

    @Override
    protected VideoCategory initEntity() {
        return new VideoCategory();
    }

    @Override
    protected BaseService<VideoCategory> getBaseService() {
        return videoCatService;
    }

}
