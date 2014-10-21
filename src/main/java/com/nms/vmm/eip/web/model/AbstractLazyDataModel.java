/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.web.model;

import com.nms.vmm.eip.entity.BaseEntity;
import com.nms.vmm.eip.service.entity.BaseService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class AbstractLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = -1137464869996262401L;

    protected abstract BaseService<T> getService();

    protected Long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public T getRowData(String rowKey) {
        return getService().find(parserRowKey(rowKey));
    }

    @Override
    public Object getRowKey(T object) {
        return ((BaseEntity) object).getId();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        boolean asc = false;
        if (sortOrder != null && sortOrder == SortOrder.ASCENDING) {
            asc = true;
        }
        this.setRowCount(getService().countForPFDatatable(filters));
        return getService().searchForPFDatatable(first, pageSize, sortField, asc, filters);
    }
}
