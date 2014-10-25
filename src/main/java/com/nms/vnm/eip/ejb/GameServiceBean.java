/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.entity.GameService;
import javax.ejb.Stateless;

@Stateless
public class GameServiceBean extends AbstratProductBean<GameCategory, Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;

    public GameServiceBean() {
        super(Game.class);
    }

}
