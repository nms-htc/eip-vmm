/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.search;

import com.nms.vmm.eip.entity.GameCategory;

/**
 *
 * @author Cuong
 */
public class GameEntryCriteria extends ProductCriteria {
    private GameCategory categories;

    public GameCategory getCategories() {
        return categories;
    }

    public void setCategories(GameCategory categories) {
        this.categories = categories;
    }
}
