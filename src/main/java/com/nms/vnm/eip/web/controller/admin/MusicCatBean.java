/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.MusicCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicCatBean extends AbstractManagedBean<MusicCategory> {

    private static final long serialVersionUID = -8467672057280121204L;

    @EJB
    MusicCategoryService musicCatService;

    @Override
    protected MusicCategory initEntity() {
        return new MusicCategory();
    }

    @Override
    protected BaseService<MusicCategory> getBaseService() {
        return musicCatService;
    }

}
