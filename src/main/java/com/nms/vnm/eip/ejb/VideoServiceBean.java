/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Video;
import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.entity.VideoService;
import javax.ejb.Stateless;

@Stateless
public class VideoServiceBean extends AbstratProductBean<VideoCategory, Video> implements VideoService {

    private static final long serialVersionUID = -1210801531435011082L;

    public VideoServiceBean() {
        super(Video.class);
    }

}
