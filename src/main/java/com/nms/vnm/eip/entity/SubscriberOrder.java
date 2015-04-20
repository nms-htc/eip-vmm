/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuongnt
 */
@Entity
@Table(name = "SUBSCRIBERORDER")
@XmlRootElement
public class SubscriberOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERID")
    private BigDecimal orderid;
    @Column(name = "USERID")
    private BigInteger userid;
    @Size(max = 300)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "MODIFIEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "MERCHANTID")
    private BigInteger merchantid;
    @Column(name = "TELCOID")
    private BigInteger telcoid;
    @Column(name = "AGENTID")
    private BigInteger agentid;
    @Size(max = 300)
    @Column(name = "CHANNEL")
    private String channel;
    @Size(max = 300)
    @Column(name = "ORDERTYPE")
    private String ordertype;
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @Size(max = 300)
    @Column(name = "ORDERNO")
    private String orderno;
    @Column(name = "CYCLEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cycledate;
    @Column(name = "SUBSCRIBERID")
    private BigInteger subscriberid;
    @Column(name = "SUBPRODUCTID")
    private BigInteger subproductid;
    @Column(name = "SUBSCRIBERTYPE")
    private BigInteger subscribertype;
    @Size(max = 300)
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "PRODUCTID")
    private BigInteger productid;
    @Size(max = 300)
    @Column(name = "SKU")
    private String sku;
    @Column(name = "CAMPAIGNID")
    private BigInteger campaignid;
    @Column(name = "OFFERPRICE")
    private BigDecimal offerprice;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "QUANTITY")
    private BigInteger quantity;
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "SCORE")
    private BigDecimal score;
    @Size(max = 300)
    @Column(name = "CURRENCY_")
    private String currency;
    @Column(name = "DELIVERYCOUNTER")
    private BigInteger deliverycounter;
    @Size(max = 300)
    @Column(name = "SHIPPINGTO")
    private String shippingto;
    @Size(max = 300)
    @Column(name = "CONTENTTYPE")
    private String contenttype;
    @Size(max = 300)
    @Column(name = "CONTENTCODE")
    private String contentcode;
    @Size(max = 300)
    @Column(name = "CPURLREQUEST")
    private String cpurlrequest;
    @Size(max = 300)
    @Column(name = "DELIVERY_STATUS")
    private String deliveryStatus;
    @Column(name = "REASONID")
    private BigInteger reasonid;
    @Column(name = "STATUS")
    private BigInteger status;
    @Column(name = "EXPORTSTATUS")
    private BigInteger exportstatus;
    @Size(max = 300)
    @Column(name = "CAUSE")
    private String cause;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 300)
    @Column(name = "SERIAL")
    private String serial;
    @Size(max = 300)
    @Column(name = "MASTERCP")
    private String mastercp;
    @Size(max = 300)
    @Column(name = "SUBCP")
    private String subcp;
    @Size(max = 50)
    @Column(name = "SERVICEADDR")
    private String serviceaddr;

    public SubscriberOrder() {
    }

    public SubscriberOrder(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getOrderid() {
        return orderid;
    }

    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public BigInteger getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(BigInteger merchantid) {
        this.merchantid = merchantid;
    }

    public BigInteger getTelcoid() {
        return telcoid;
    }

    public void setTelcoid(BigInteger telcoid) {
        this.telcoid = telcoid;
    }

    public BigInteger getAgentid() {
        return agentid;
    }

    public void setAgentid(BigInteger agentid) {
        this.agentid = agentid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Date getCycledate() {
        return cycledate;
    }

    public void setCycledate(Date cycledate) {
        this.cycledate = cycledate;
    }

    public BigInteger getSubscriberid() {
        return subscriberid;
    }

    public void setSubscriberid(BigInteger subscriberid) {
        this.subscriberid = subscriberid;
    }

    public BigInteger getSubproductid() {
        return subproductid;
    }

    public void setSubproductid(BigInteger subproductid) {
        this.subproductid = subproductid;
    }

    public BigInteger getSubscribertype() {
        return subscribertype;
    }

    public void setSubscribertype(BigInteger subscribertype) {
        this.subscribertype = subscribertype;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public BigInteger getProductid() {
        return productid;
    }

    public void setProductid(BigInteger productid) {
        this.productid = productid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigInteger getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(BigInteger campaignid) {
        this.campaignid = campaignid;
    }

    public BigDecimal getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(BigDecimal offerprice) {
        this.offerprice = offerprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigInteger getDeliverycounter() {
        return deliverycounter;
    }

    public void setDeliverycounter(BigInteger deliverycounter) {
        this.deliverycounter = deliverycounter;
    }

    public String getShippingto() {
        return shippingto;
    }

    public void setShippingto(String shippingto) {
        this.shippingto = shippingto;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getContentcode() {
        return contentcode;
    }

    public void setContentcode(String contentcode) {
        this.contentcode = contentcode;
    }

    public String getCpurlrequest() {
        return cpurlrequest;
    }

    public void setCpurlrequest(String cpurlrequest) {
        this.cpurlrequest = cpurlrequest;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public BigInteger getReasonid() {
        return reasonid;
    }

    public void setReasonid(BigInteger reasonid) {
        this.reasonid = reasonid;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getExportstatus() {
        return exportstatus;
    }

    public void setExportstatus(BigInteger exportstatus) {
        this.exportstatus = exportstatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMastercp() {
        return mastercp;
    }

    public void setMastercp(String mastercp) {
        this.mastercp = mastercp;
    }

    public String getSubcp() {
        return subcp;
    }

    public void setSubcp(String subcp) {
        this.subcp = subcp;
    }

    public String getServiceaddr() {
        return serviceaddr;
    }

    public void setServiceaddr(String serviceaddr) {
        this.serviceaddr = serviceaddr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubscriberOrder)) {
            return false;
        }
        SubscriberOrder other = (SubscriberOrder) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.vnm.eip.entity.Subscriberorder[ orderid=" + orderid + " ]";
    }

}
