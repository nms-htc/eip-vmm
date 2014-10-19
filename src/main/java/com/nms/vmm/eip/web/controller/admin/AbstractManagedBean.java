/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */
package com.nms.vmm.eip.web.controller.admin;

import com.nms.vmm.eip.entity.BaseEntity;
import com.nms.vmm.eip.service.entity.BaseService;
import com.nms.vmm.eip.web.model.AbstractLazyDataModel;
import com.nms.vmm.eip.web.util.JsfUtil;
import com.nms.vmm.eip.web.util.MessageUtil;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;


/**
 * Abtrast class that implement all basic functionalities managed bean for entities.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 */
public abstract class AbstractManagedBean<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = 8024568564171342875L;
    protected static final String REQUEST_SUCCESS_MESSAGE = "your-request-has-been-successfully-implemented";
    protected static final String REQUEST_FAIL_MESSAGE = "your-request-fails";

    protected T current;
    protected LazyDataModel<T> model;
    private SelectItem[] selectItems;

    public void resetEntity() {
        current = null;
    }

    public void prepareEntity(T entity) {
        current = entity;
    }

    /**
     * Call back method persist action
     */
    protected void onBeforePersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onAfterPersist() {
        current = initEntity();
    }

    /**
     * Call back method persist action
     */
    protected void onPersistSuccess() {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method persist action
     *
     * @param t
     */
    protected void onPersistError(Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Persist entity to db
     */
    public void persist() {
        try {
            onBeforePersist();
            getBaseService().persist(current);
            onPersistSuccess();
        } catch (Exception e) {
            onPersistError(e);
        }
        onAfterPersist();
    }

    /**
     * Call back method update action
     */
    protected void onBeforeUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onAfterUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onSuccessUpdate() {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method update action
     *
     * @param t
     */
    protected void onErrorUpdate(Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Update entity and save to db
     */
    public void update() {
        try {
            onBeforeUpdate();
            getBaseService().update(current);
            onSuccessUpdate();
        } catch (Exception e) {
            onErrorUpdate(e);
        }
        onAfterUpdate();
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onBeforeRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onAfterRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onSuccessRemove(T entity) {
        MessageUtil.addGlobalInfoMessage(REQUEST_SUCCESS_MESSAGE);
    }

    /**
     * Call back method remove action
     *
     * @param entity
     * @param t
     */
    protected void onErrorRemove(T entity, Throwable t) {
        MessageUtil.addGlobalErrorMessage(REQUEST_FAIL_MESSAGE, t);
    }

    /**
     * Remove entity from db
     *
     * @param entity entity instance for removing
     */
    public void remove(T entity) {
        onBeforeRemove(entity);
        try {
            getBaseService().remove(entity);
            onSuccessRemove(entity);
        } catch (Exception e) {
            onErrorRemove(entity, e);
        }
        onAfterRemove(entity);
    }

    /**
     * Initilize a new instance of the entity;
     *
     * @return new instanse of entity
     */
    protected abstract T initEntity();
    
    /**
     * Factory method for initilize a new instance of LazyDataModel (Primefaces)
     *
     * @return new instance of LazyDataModel for the entity.
     */
    protected LazyDataModel<T> initDataModel() {
        return new AbstractLazyDataModel<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected BaseService<T> getService() {
                return getBaseService();
            }
        };
    }

    /**
     * Factoty method for BasicService EJB
     *
     * @return BasicSerivce instanse
     */
    protected abstract BaseService<T> getBaseService();

    /* getters and setters */
    public T getCurrent() {

        if (current == null) {
            current = initEntity();
        }

        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LazyDataModel<T> getModel() {
        if (model == null) {
            model = initDataModel();
        }
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }

    public SelectItem[] getSelectItems() {
        if (selectItems == null) {
            selectItems = JsfUtil.getSelectItems(getBaseService().findAll(), false);
        }
        return selectItems;
    }

    public void setSelectItems(SelectItem[] selectItems) {
        this.selectItems = selectItems;
    }
    
    protected void processEntity(Consumer<T> consumer, T entity, String successMsg, String errorMsg) {
        try {
            consumer.accept(entity);
            MessageUtil.addGlobalInfoMessage(successMsg);
        } catch (Exception e) {
            MessageUtil.addGlobalErrorMessage(errorMsg, e);
        }
    }
}
