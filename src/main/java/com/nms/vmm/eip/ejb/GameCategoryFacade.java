/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.GameCategory;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cuong
 */
@Stateless
public class GameCategoryFacade extends AbstractFacade<GameCategory> implements Serializable {
    private static final long serialVersionUID = 2937056856062193376L;
    
    @PersistenceContext
    private EntityManager em;

    public GameCategoryFacade() {
        super(GameCategory.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
