/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.convertor;

import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.GameCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "gameCatConvertor")
public class GameCatConvertor extends AbstractEntityConvertor<GameCategory> {
    
    @EJB
    private GameCategoryService gameCategoryService;

    @Override
    protected BaseService<GameCategory> getBaseService() {
        return gameCategoryService;
    }

    @Override
    protected Class<GameCategory> getEntityClass() {
        return GameCategory.class;
    }
}
