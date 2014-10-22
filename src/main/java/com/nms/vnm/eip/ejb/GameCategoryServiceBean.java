/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.entity.GameCategoryService;
import javax.ejb.Stateless;

@Stateless
public class GameCategoryServiceBean extends AbstractFacadeBean<GameCategory> implements GameCategoryService {

    private static final long serialVersionUID = -407380901258123522L;

    public GameCategoryServiceBean() {
        super(GameCategory.class);
    }
}
