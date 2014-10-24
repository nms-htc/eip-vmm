/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.Music;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.MusicService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicBean extends AbstractProductBean<Music> {

    private static final long serialVersionUID = 6621902050457501495L;

    @EJB
    private MusicService musicService;

    @Override
    protected Music initEntity() {
        return new Music();
    }

    @Override
    protected BaseService<Music> getBaseService() {
        return musicService;
    }

}
