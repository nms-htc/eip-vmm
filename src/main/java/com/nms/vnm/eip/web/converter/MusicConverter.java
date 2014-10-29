/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.Music;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.MusicService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("musicConverter")
public class MusicConverter extends AbstractEntityConverter<Music> {

    @EJB
    private MusicService musicService;

    @Override
    protected BaseService<Music> getBaseService() {
        return musicService;
    }

    @Override
    protected Class<Music> getEntityClass() {
        return Music.class;
    }

}
