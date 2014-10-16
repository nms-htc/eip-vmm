/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ws;

import com.nms.vmm.eip.ejb.GameCategoryFacade;
import com.nms.vmm.eip.ejb.GameEntryFacade;
import com.nms.vmm.eip.entity.GameCategory;
import com.nms.vmm.eip.entity.Game;
import com.nms.vmm.eip.search.OrderType;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Cuong
 */
@WebService(serviceName = "EipContentService",
        name = "EipContentPortType",
        portName = "EipContentPort"
)
public class EipContentServiceImpl {
    @EJB
    private GameCategoryFacade gameCategoryFacade;
    @EJB
    private GameEntryFacade gameEntryFacade;
    
    @WebMethod(operationName = "getAllGameCategory")
    public List<GameCategory> getAllGameCategory() {
        return gameCategoryFacade.findAll();
    }

    @WebMethod(operationName = "searchGameEntry")
    public List<Game> searchGameEntry(@WebParam(name = "categoryId") Long categoryId, 
            @WebParam(name = "keywords") String keywords, @WebParam(name = "orderType") OrderType orderType, 
            @WebParam(name = "start") int start, @WebParam(name = "range") int range) {
        return gameEntryFacade.search(categoryId, keywords, orderType, start, range);
    }

    @WebMethod(operationName = "countGameEntry")
    public int countGameEntry(@WebParam(name = "categoryId") Long categoryId, 
            @WebParam(name = "keyword") String keyword) {
        return gameEntryFacade.count(categoryId, keyword);
    }
}
