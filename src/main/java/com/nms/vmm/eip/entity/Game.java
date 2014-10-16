/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.entity;

import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("Game")
@XmlRootElement
public class Game extends Product {
    
    private static final long serialVersionUID = -2110867128144045606L;
    
    public enum Flatform {

        ANDROID, IOS, JAVA, WINDOW_PHONE, OTHER;
    }   

    @Size(max = 2000)
    @Column(name = "DEVICES_SUPPORT", length = 2000)
    private String devicesSupport;

    @ElementCollection(targetClass = Flatform.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "EIP_FLATFORM", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name = "FLATFORM")
    private Collection<Flatform> flatforms;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private GameCategory category;

    public String getDevicesSupport() {
        return devicesSupport;
    }

    public void setDevicesSupport(String devicesSupport) {
        this.devicesSupport = devicesSupport;
    }

    public Collection<Flatform> getFlatforms() {
        return flatforms;
    }

    public void setFlatforms(Collection<Flatform> flatforms) {
        this.flatforms = flatforms;
    }

    @XmlTransient
    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

}