/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Cuong
 */
@Entity
@Table(name = "EIP_PRODUCT")
@SuppressWarnings("serial")
public abstract class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Size(max = 75, message = "{product.code}")
    @Basic(optional = false)
    private String code;

    @Size(max = 75, message = "{product.cpcode}")
    @Basic(optional = false)

    private String cpCode;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Basic(optional = false)
    @Size(max = 250, message = "{product.title}")
    private String title;

    @Size(max = 300, message = "{product.description}")
    @Basic(optional = false)
    private String description;

    @Lob
    private String specification;

    @Column(name = "DOWNLOAD_COUNT")
    private int downloadCount;
    
    @Column(name = "IS_HOT")
    private boolean hot;

    @Column(name = "PROMO_PRICE")
    private double promoPrice;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnailUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EIP_SCREENSHORT", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name = "SCREENSHORT_URL")
    private List<String> screenShorts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getScreenShorts() {
        return screenShorts;
    }

    public void setScreenShorts(List<String> screenShorts) {
        this.screenShorts = screenShorts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.nms.vmm.eip.entity.Product[ id=" + id + " ]";
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}
