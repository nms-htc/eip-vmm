/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.entity;

import com.nms.vmm.eip.entity.validation.Url;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EIP_PRODUCT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@XmlRootElement
public abstract class Product extends BaseEntity {
    
    private static final long serialVersionUID = 1600980462406964970L;

    @NotNull
    @Size(max = 150)
    @Column(name = "CODE", length = 150, nullable = false)
    protected String code;

    @NotNull
    @Size(max = 75)
    @Column(name = "CPCODE", length = 75, nullable = false)
    protected String cpCode;

    @NotNull
    @Size(max = 250)
    @Column(name = "TITLE", length = 250, nullable = false)
    protected String title;

    @Lob
    @Column(name = "SPEC")
    protected String specification;

    @Column(name = "DOWNLOAD_COUNT")
    @Min(0)
    protected int downloadCount;
    
    @Column(name = "VIEW_COUNT")
    @Min(0)
    protected int viewCount;

    @Column(name = "IS_HOT")
    protected boolean hot;

    @Column(name = "PROMO_PRICE")
    protected double promoPrice;

    @Column(name = "PRICE")
    protected double price;
    
    @Url
    @Column(name = "THUMBNAIL_URL")
    protected String thumbnailUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EIP_SCREENSHORT", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name = "SCREENSHORT_URL")
    protected List<String> screenShorts;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected User user;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
