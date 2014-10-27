/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.service.entity;

import com.nms.vnm.eip.entity.Category;
import com.nms.vnm.eip.entity.Product;
import java.util.List;

public interface ProductService<E extends Product, C extends Category> extends BaseService<E> {

    public List<E> findByCat(int start, int range, C category, String orderField, boolean asc);

    public List<E> findExcludeCurrent(int start, int range, E product);
    
    public List<E> getPromotions(int start, int range,C category, String orderField, boolean asc);
    
    public List<E> getFrees(int start, int range,C category, String orderField, boolean asc);
    
    public int countPromotions(C category);
    
    public int countFrees(C category);

    public int countByCat(C category);
}
