/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.entity;

import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cuong
 */
@Entity
public class GameEntry extends Product {
    private static final long serialVersionUID = 771010982456643317L;
    
    @Column(name = "DEVICES_SUPPORT")
    @Size(max = 2000)
    private String devicesSupport;
    
    @ElementCollection(targetClass = Flatform.class)
    @Enumerated
    @CollectionTable(name = "EIP_FLATFORM", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name = "FLATFORM")
    private Collection<Flatform> flatforms;
    
    @ManyToOne
    @MapKeyColumn(name = "CATEGORY_ID")
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
    
    @Override
    public String toString() {
        return "com.nms.vmm.eip.entity.GameEntry[ id=" + id + " ]";
    }
}
