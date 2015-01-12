/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vnm.eip.service;

import com.nms.vnm.eip.entity.Product;
import com.nms.vnm.eip.exception.AppException;
import com.nms.vnm.eip.exception.ErrorInfo;
import com.nms.vnm.eip.web.util.MessageUtil;
import com.nms.ws.charging.ChargingWebserviceImpl;
import com.nms.ws.charging.ChargingWebserviceImplService;
import com.nms.ws.charging.ContentPurcharseReq;
import com.nms.ws.charging.ContentPurcharseRes;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Dependent
public class ChargingServiceImpl implements ChargingService {

    private static final long serialVersionUID = 8077722183941509246L;
    private static final String CHARGING_SERVICE_URL = "http://10.8.224.37:8080/EntertainmentWS/ChargingWebserviceImpl?wsdl";
    private static final Logger LOGGER = Logger.getLogger(ChargingServiceImpl.class.getName());

    @Inject
    private MobileChecker mobileChecker;

    @Override
    public FacesMessage chargeProduct(Product product) {
        
        if (product != null) {
            FacesMessage message = null;
            
            ChargingWebserviceImplService chargingServicePort = new ChargingWebserviceImplService();
            ChargingWebserviceImpl chargingService = chargingServicePort.getChargingWebserviceImplPort();
            ContentPurcharseReq request = new ContentPurcharseReq();
            request.setUserName("");
            request.setPassword("");
            //request.setTimeout(60000);
            request.setCpName(product.getCpCode());
            request.setContentCode(product.getCode());
            request.setIsdn(mobileChecker.getPhoneNumber());
            request.setOsCode(String.valueOf(mobileChecker.getOsCode()));
            request.setPrice(product.getPrice());
            request.setShortCode("");
            ContentPurcharseRes response = chargingService.chargeItems(request);
            
            int resutl = response.getResult();
            String content = response.getContent();
            String detail = response.getDetail();
            
            if (resutl == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        MessageUtil.getBundleMessage("vnm-purcharse-success"), 
                        "");
                RequestContext.getCurrentInstance().execute("window.open('" + content + "', '_blank')");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "vnm-purcharse-error", content + " - " + detail);
            }
            
            return message;
        } else {
            throw new AppException(ErrorInfo.UNKNOW_ERROR);
        }
    }

}
