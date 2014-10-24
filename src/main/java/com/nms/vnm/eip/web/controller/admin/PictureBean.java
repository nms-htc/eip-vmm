/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.Picture;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PictureService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PictureBean extends AbstractProductBean<Picture> {

    private static final long serialVersionUID = 2219397982611609176L;
    @EJB
    private PictureService pictureService;

    @Override
    protected Picture initEntity() {
        return new Picture();
    }

    @Override
    protected BaseService<Picture> getBaseService() {
        return pictureService;
    }

}
