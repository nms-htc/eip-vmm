/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.web.convertor;

import com.nms.vmm.eip.entity.GameCategory;
import com.nms.vmm.eip.service.entity.BaseService;
import com.nms.vmm.eip.service.entity.GameCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = GameCategory.class, value = "gameCategoryConvertor")
public class GameCategoryConvertor extends AbstractEntityConvertor<GameCategory> {
    
    @EJB
    private GameCategoryService gameCategoryService;

    @Override
    protected BaseService<GameCategory> getBaseService() {
        return gameCategoryService;
    }
}
