/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS).
 * All rights reserved.
 */
package com.nms.vmm.eip.service.entity;

import java.io.Serializable;
import java.util.List;


/**
 * Basic functionalities of ejb beans.
 *
 * @author Nguyen Trong Cuong (cuongnt1987@gmail.com)
 * @since 16/09/2014
 * @version 1.0
 * @param <T> Entity Class Type
 */
public interface BaseService<T> extends Serializable {

    /**
     * Find entity by id object.
     * @param id
     * @return 
     */
    public T find(Object id);

    /**
     * Find all entities.
     * @return all of entities in database.
     */
    public List<T> findAll();

    /**
     * count all record in db.
     * @return number of entity records.
     */
    public int countAll();

    /**
     * Persist entity to db
     * @param entity object to persist.
     * @return entity after persist.
     */
    public T persist(T entity);

    /**
     * Update entity
     * @param entity
     * @return 
     */
    public T update(T entity);

    /**
     * Delete entity from db.
     * @param entity 
     */
    public void remove(T entity);
}