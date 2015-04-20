/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.SubscriberOrder;
import com.nms.vnm.eip.entity.User;
import com.nms.vnm.eip.service.entity.SubscriberOrderService;
import com.nms.vnm.eip.service.entity.UserService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author cuongnt
 */
@Named
@ViewScoped
public class RevenueBean implements Serializable {

    private static final long serialVersionUID = -713278848861152016L;

    @Inject
    private User currentUser;

    private String cpCode;
    private Date startOrderDate;
    private Date endOrderDate;
    private LazyDataModel<SubscriberOrder> model;
    private SelectItem[] cpCodeSelectItem;
    private double totalAmount;

    @EJB
    private SubscriberOrderService subscriberOrderService;
    @EJB
    private UserService userService;

    public RevenueBean() {
    }

    public String getCpCode() {
        if (currentUser.getGroups().contains(User.Group.Admin)) {
            // Nothing
        } else {
            cpCode = currentUser.getCode();
        }
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public Date getStartOrderDate() {
        if (startOrderDate == null) {
            startOrderDate = truncAndAddDate(new Date(), -1);
        } else {
            startOrderDate = truncDate(startOrderDate);
        }
        return startOrderDate;
    }

    public void setStartOrderDate(Date startOrderDate) {
        this.startOrderDate = startOrderDate;
    }

    public Date getEndOrderDate() {
        if (endOrderDate == null) {
            endOrderDate = truncDate(new Date());
        } else {
            endOrderDate = truncDate(endOrderDate);
        }
        return endOrderDate;
    }

    public void setEndOrderDate(Date endOrderDate) {
        this.endOrderDate = endOrderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SelectItem[] getCpCodeSelectItem() {
        if (cpCodeSelectItem == null) {
            List<User> users = userService.findAll();
            cpCodeSelectItem = new SelectItem[users.size()];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                cpCodeSelectItem[i] = new SelectItem(user.getCode(), user.getCode());
            }
        }
        return cpCodeSelectItem;
    }

    public LazyDataModel<SubscriberOrder> getModel() {
        if (model == null) {
            model = new LazyDataModel<SubscriberOrder>() {
                private static final long serialVersionUID = 3109256773218160485L;

                @Override
                public SubscriberOrder getRowData(String rowKey) {
                    return subscriberOrderService.find(BigDecimal.valueOf(Double.parseDouble(rowKey)));
                }

                @Override
                public Object getRowKey(SubscriberOrder object) {
                    return object.getOrderid();
                }

                @Override
                public List<SubscriberOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    setRowCount(subscriberOrderService.count(getCpCode(), getStartOrderDate(), getEndOrderDate(), filters));
                    try {
                        setTotalAmount(subscriberOrderService.calculateTotalAmount(getCpCode(), getStartOrderDate(), getEndOrderDate(), filters));
                    } catch (Exception e) {
                        setTotalAmount(0);
                    }

                    boolean asc = false;
                    if (sortOrder != null && sortOrder == SortOrder.ASCENDING) {
                        asc = true;
                    }
                    return subscriberOrderService.search(getCpCode(), getStartOrderDate(), getEndOrderDate(), first, pageSize, sortField, asc, filters);

                }
            };
        }
        return model;
    }

    public void setModel(LazyDataModel<SubscriberOrder> model) {
        this.model = model;
    }

    private Date truncDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date truncAndAddDate(Date date, int dateOffset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, dateOffset);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
