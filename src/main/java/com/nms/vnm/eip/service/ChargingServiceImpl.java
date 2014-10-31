/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vnm.eip.service;

import com.nms.vnm.eip.entity.Product;
import com.nms.vnm.eip.exception.AppException;
import com.nms.vnm.eip.exception.ErrorInfo;
import com.nms.ws.charging.ChargingWebserviceImpl;
import com.nms.ws.charging.ChargingWebserviceImplService;
import com.nms.ws.charging.ContentPurcharseReq;
import com.nms.ws.charging.ContentPurcharseRes;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ChargingServiceImpl implements ChargingService {

    private static final long serialVersionUID = 8077722183941509246L;

    @Inject
    private MobileChecker mobileChecker;

    @Override
    public int chargeProduct(Product product) {
        if (product != null) {
            ChargingWebserviceImplService chargingServicePort = new ChargingWebserviceImplService();
            ChargingWebserviceImpl chargingService = chargingServicePort.getChargingWebserviceImplPort();
            ContentPurcharseReq request = new ContentPurcharseReq();
            request.setUserName("");
            request.setPassword("");
            request.setTimeout(10);
            request.setCpName(product.getCpCode());
            request.setContentCode(product.getCode());
            request.setIsdn(mobileChecker.getPhoneNumber());
            request.setOsCode(String.valueOf(mobileChecker.getOsCode()));
            request.setPrice(product.getPrice());
            request.setShortCode("");
            ContentPurcharseRes response = chargingService.chargeItems(request);
            return response.getResult();
        } else {
            throw new AppException(ErrorInfo.UNKNOW_ERROR);
        }
    }

}