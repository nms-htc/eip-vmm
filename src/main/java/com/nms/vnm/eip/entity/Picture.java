/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("Picture")
@XmlRootElement
public class Picture extends Product {

    private static final long serialVersionUID = 496486137872024515L;
    
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @XmlTransient
    protected PictureCategory category;

    public Picture() {
    }
    
    public PictureCategory getCategory() {
        return category;
    }

    public void setCategory(PictureCategory category) {
        this.category = category;
    }
    
}
