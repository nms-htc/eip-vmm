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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Dependent
public class ChargingServiceImpl implements ChargingService {

    private static final long serialVersionUID = 8077722183941509246L;
    private static final String CHARGING_SERVICE_URL = "http://localhost/EntertainmentWS/ChargingWebserviceImpl?wsdl";
    private static final Logger LOGGER = Logger.getLogger(ChargingServiceImpl.class.getName());

    @Inject
    private MobileChecker mobileChecker;

    @Override
    public FacesMessage chargeProduct(Product product) {
        java.net.URL url;
        try {
            url = new URL(CHARGING_SERVICE_URL);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Charing service WSDL URL Malformed", CHARGING_SERVICE_URL);
            return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when connect to Charging service!", CHARGING_SERVICE_URL);
        }
        
        if (product != null) {
            FacesMessage message;
            
            ChargingWebserviceImplService chargingServicePort = new ChargingWebserviceImplService(url);
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
