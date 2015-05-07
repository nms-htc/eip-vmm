/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.service.entity;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.search.OrderType;
import java.util.List;

public interface GameService extends ProductService<Game, GameCategory> {

    public List<Game> search(Long categoryId, String keywords, OrderType orderType, int page, int range, Game.Flatform flatform);

}
