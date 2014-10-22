/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.entity.VideoCategoryService;
import javax.ejb.Stateless;

@Stateless
public class VideoCategoryServiceBean extends AbstractFacadeBean<VideoCategory> implements VideoCategoryService {

    private static final long serialVersionUID = -6276138478905188216L;

    public VideoCategoryServiceBean() {
        super(VideoCategory.class);
    }

}
