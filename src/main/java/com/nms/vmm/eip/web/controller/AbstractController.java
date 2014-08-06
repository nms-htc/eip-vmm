/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.AbstractFacade;
import com.nms.vmm.eip.web.util.JsfUtil;
import com.nms.vmm.eip.web.util.MessageUtil;
import com.nms.vmm.eip.web.util.PaginationHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Cuong
 * @param <T>
 */
public abstract class AbstractController<T> {
    
    protected abstract AbstractFacade<T> getFacade();
    protected T current;
    protected Class<T> itemClass;
    protected DataModel<T> items;
    protected PaginationHelper paginationHelper;

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /* Getters And Setters */
    
        public T getCurrent() {
        if (current == null) {
            try {
                current = itemClass.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public DataModel<T> getItems() {
        if (items == null) {
            items = getPaginationHelper().createPageDataModel();
        }
        return items;
    }

    public void setItems(DataModel<T> items) {
        this.items = items;
    }

    public PaginationHelper getPaginationHelper() {
        if (paginationHelper == null) {
            paginationHelper = new PaginationHelper(10, getFacade().countAll()) {

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(
                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize() - 1})
                    );
                }
            };
        }
        return paginationHelper;
    }

    public void setPaginationHelper(PaginationHelper paginationHelper) {
        this.paginationHelper = paginationHelper;
    }
    
    /* Controller method */
    
    public String prepareCreate() {
        current = null;
        return "create";
    }
    
    public String prepareEdit() {
        current = items.getRowData();
        return "edit";
    }
    
    public String create() {
        if (validate(current)) {
            try {
                getFacade().create(current);
                MessageUtil.addGlobalSuccessMessage();
                items = null;
                paginationHelper = null;
                return "list";
            } catch (Exception e) {
                MessageUtil.addGlobalPersistenceErrorMessage();
                return null;
            }
        }
        return null;
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            MessageUtil.addGlobalSuccessMessage();
            items = null;
            paginationHelper = null;
            return "list";
        } catch (Exception e) {
            items = null;
            paginationHelper = null;
            MessageUtil.addGlobalPersistenceErrorMessage();
            return null;
        }
    }

    public String delete() {
        current = items.getRowData();
        try {
            getFacade().remove(current);
            MessageUtil.addGlobalSuccessMessage();
            items = null;
            paginationHelper = null;
            return "list";
        } catch (Exception e) {
            items = null;
            paginationHelper = null;
            JsfUtil.addErrorMessage(JsfUtil.getRootCause(e).getLocalizedMessage());
            return null;
        }
    }
    
    public String nextPage() {
        paginationHelper.nextPage();
        items = null;
        return "list?faces-redirect=true";
    }

    public String previewPage() {
        paginationHelper.previewPage();
        items = null;
        return "list?faces-redirect=true";
    }

    public String startPage() {
        paginationHelper.goToStartPage();
        items = null;
        return "list?faces-redirect=true";
    }
    
    public String endPage() {
        paginationHelper.goToEndPage();
        items = null;
        return "list?faces-redirect=true";
    }
    
    protected abstract boolean validate(T entity);
}
