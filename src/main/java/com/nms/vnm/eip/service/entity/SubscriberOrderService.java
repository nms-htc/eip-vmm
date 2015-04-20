/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.service.entity;

import com.nms.vnm.eip.entity.SubscriberOrder;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cuongnt
 */
public interface SubscriberOrderService extends Serializable {
    
    /**
     * Find entity by id object.
     *
     * @param id
     * @return
     */
    public SubscriberOrder find(Object id);

    /**
     * Find all entities.
     *
     * @return all of entities in database.
     */
    public List<SubscriberOrder> findAll();

    /**
     * count all record in db.
     *
     * @return number of entity records.
     */
    public int countAll();

    /**
     * Persist entity to db
     *
     * @param entity object to persist.
     * @return entity after persist.
     */
    public SubscriberOrder persist(SubscriberOrder entity);

    /**
     * Update entity
     *
     * @param entity
     * @return
     */
    public SubscriberOrder update(SubscriberOrder entity);

    /**
     * Delete entity from db.
     *
     * @param entity
     */
    public void remove(SubscriberOrder entity);

    /**
     * Filter method for primerfaces lazy data table.
     *
     * @param start
     * @param range
     * @param sortField
     * @param asc
     * @param filters
     * @return
     */
    public List<SubscriberOrder> search(int start, int range, String sortField, boolean asc, Map<String, Object> filters);

    /**
     * Using in primeface lazy data model.
     *
     * @param filters
     * @return
     */
    public int count(Map<String, Object> filters);
    
    
    /**
     * Filter method for primerfaces lazy data table.
     *
     * @param cpCode
     * @param starOrderDate
     * @param endOrderDate
     * @param start
     * @param range
     * @param sortField
     * @param asc
     * @param filters
     * @return
     */
    public List<SubscriberOrder> search(String cpCode, Date starOrderDate, Date endOrderDate, int start, int range, String sortField, boolean asc, Map<String, Object> filters);

    /**
     * Using in primeface lazy data model.
     *
     * @param cpCode
     * @param starOrderDate
     * @param endOrderDate
     * @param filters
     * @return
     */
    public int count(String cpCode, Date starOrderDate, Date endOrderDate,Map<String, Object> filters);
    
    
    public double calculateTotalAmount(String cpCode, Date starOrderDate, Date endOrderDate,Map<String, Object> filters);
}
