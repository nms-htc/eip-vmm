/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.entity;

import com.nms.vnm.eip.entity.validation.Url;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOHONGPHUC
 */
@Entity
@DiscriminatorValue("Banner")
@XmlRootElement
public class Banner extends Product {
    private static final long serialVersionUID = 496486137872024512L;

    @Url
    @Size(max = 500)
    @Column(name="REVIEW_URL", length = 500)
    protected String reviewUrl;

    public Banner() {
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }
}
