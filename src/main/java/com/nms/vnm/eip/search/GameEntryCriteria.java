/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vnm.eip.search;

import com.nms.vnm.eip.entity.GameCategory;

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
