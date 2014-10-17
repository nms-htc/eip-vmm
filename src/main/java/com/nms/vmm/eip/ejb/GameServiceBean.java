/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Game;
import com.nms.vmm.eip.service.entity.GameService;
import javax.ejb.Stateless;

@Stateless
public class GameServiceBean extends AbstractFacadeBean<Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;

    public GameServiceBean() {
        super(Game.class);
    }

}
