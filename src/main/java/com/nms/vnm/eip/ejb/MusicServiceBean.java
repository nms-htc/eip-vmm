/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Music;
import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.entity.MusicService;
import javax.ejb.Stateless;

@Stateless
public class MusicServiceBean extends AbstratProductBean<MusicCategory, Music> implements MusicService {

    private static final long serialVersionUID = 4317161615998592675L;

    public MusicServiceBean() {
        super(Music.class);
    }

}
