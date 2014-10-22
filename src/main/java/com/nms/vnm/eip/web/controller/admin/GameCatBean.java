/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.GameCategoryService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GameCatBean extends AbstractManagedBean<GameCategory> {

    private static final long serialVersionUID = -5858193832490589251L;

    @EJB
    private GameCategoryService gameCatService;

    @Override
    protected GameCategory initEntity() {
        return new GameCategory();
    }

    @Override
    protected BaseService<GameCategory> getBaseService() {
        return gameCatService;
    }

}
