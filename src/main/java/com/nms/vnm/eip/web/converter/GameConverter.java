/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.converter;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.GameService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter("gameConverter")
public class GameConverter extends AbstractEntityConverter<Game> {

    @EJB
    private GameService gameService;

    @Override
    protected BaseService<Game> getBaseService() {
        return gameService;
    }

    @Override
    protected Class<Game> getEntityClass() {
        return Game.class;
    }
}
