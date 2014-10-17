/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Music;
import com.nms.vmm.eip.service.entity.MusicService;
import javax.ejb.Stateless;

@Stateless
public class MusicServiceBean extends AbstractFacadeBean<Music> implements MusicService {

    private static final long serialVersionUID = 4317161615998592675L;

    public MusicServiceBean() {
        super(Music.class);
    }

}
