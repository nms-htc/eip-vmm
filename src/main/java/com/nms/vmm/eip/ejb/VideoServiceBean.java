/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Video;
import com.nms.vmm.eip.service.entity.VideoService;
import javax.ejb.Stateless;

@Stateless
public class VideoServiceBean extends AbstractFacadeBean<Video> implements VideoService {

    private static final long serialVersionUID = -1210801531435011082L;

    public VideoServiceBean() {
        super(Video.class);
    }

}
