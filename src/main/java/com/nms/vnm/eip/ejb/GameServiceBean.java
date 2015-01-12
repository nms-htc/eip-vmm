/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.GameService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class GameServiceBean extends AbstractProductBean<GameCategory, Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;

    public GameServiceBean() {
        super(Game.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Game> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }
}
