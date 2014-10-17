/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Picture;
import com.nms.vmm.eip.service.entity.PictureService;
import javax.ejb.Stateless;

@Stateless
public class PictureServiceBean extends AbstractFacadeBean<Picture> implements PictureService {

    private static final long serialVersionUID = -1752966518637682180L;

    public PictureServiceBean() {
        super(Picture.class);
    }

}
