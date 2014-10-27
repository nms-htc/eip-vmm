/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.entity.GameService;
import com.nms.vnm.eip.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GameController extends AbstractProductController<Game, GameCategory> {

    private static final long serialVersionUID = 8560603447846518616L;

    @EJB
    private GameService gameService;

    @Override
    protected ProductService<Game, GameCategory> getProductService() {
        return gameService;
    }

}
