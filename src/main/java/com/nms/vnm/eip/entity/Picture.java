/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Picture")
@XmlRootElement
public class Picture extends Product<PictureCategory> {

    private static final long serialVersionUID = 496486137872024515L;
    
    public Picture() {
    }
}
