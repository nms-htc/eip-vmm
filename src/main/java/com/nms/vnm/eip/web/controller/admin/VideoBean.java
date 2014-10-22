/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.Video;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoBean extends AbstractManagedBean<Video> {

    private static final long serialVersionUID = -1514605817248554643L;
    @EJB
    private VideoService videoService;

    @Override
    protected Video initEntity() {
        return new Video();
    }

    @Override
    protected BaseService<Video> getBaseService() {
        return videoService;
    }

}
