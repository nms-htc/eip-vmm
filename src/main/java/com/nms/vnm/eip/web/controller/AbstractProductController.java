/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Category;
import com.nms.vnm.eip.entity.Product;
import java.util.List;

public abstract class AbstractProductController<T extends Product, C extends Category> {

    protected T current;
    protected C category;

    protected List<T> listByCat;
    protected List<T> listExcludeCurrent;

    public C getCategory() {
        return category;
    }

    public void setCategory(C category) {
        this.category = category;
    }

    protected List<T> hots;
    protected List<T> tops;
    protected List<T> news;
    protected List<T> frees;
    protected List<T> promos;

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }
    
    public List<T> getListByCat() {
        return listByCat;
    }

    public List<T> getListExcludeCurrent() {
        return listExcludeCurrent;
    }

    public List<T> getHots() {
        return hots;
    }

    public List<T> getTops() {
        return tops;
    }

    public List<T> getNews() {
        return news;
    }

    public List<T> getFrees() {
        return frees;
    }

    public List<T> getPromos() {
        return promos;
    }
}
