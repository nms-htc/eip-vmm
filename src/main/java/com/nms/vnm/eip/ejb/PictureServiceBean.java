/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Picture;
import com.nms.vnm.eip.entity.PictureCategory;
import com.nms.vnm.eip.service.entity.PictureService;
import javax.ejb.Stateless;

@Stateless
public class PictureServiceBean extends AbstratProductBean<PictureCategory, Picture> implements PictureService {

    private static final long serialVersionUID = -1752966518637682180L;

    public PictureServiceBean() {
        super(Picture.class);
    }

}
