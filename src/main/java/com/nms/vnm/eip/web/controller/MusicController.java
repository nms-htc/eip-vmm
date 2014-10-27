/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Music;
import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.entity.MusicService;
import com.nms.vnm.eip.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MusicController extends AbstractProductController<Music, MusicCategory> {

    private static final long serialVersionUID = -6326412027099695784L;

    @EJB
    private MusicService musicService;

    @Override
    protected ProductService<Music, MusicCategory> getProductService() {
        return musicService;
    }
}
