/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.GameEntryFacade;
import com.nms.vmm.eip.entity.Product;
import com.nms.vmm.eip.web.util.PaginationHelper;
import com.nms.vmm.eip.web.util.UserAgentInfo;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

/**
 *
 * @author Cuong
 */
@Named
@SessionScoped
public class ProductSearchController implements Serializable {
    
    private static final long serialVersionUID = 1804968382408285051L;
    
    @EJB
    private GameEntryFacade gameEntryFacade;
    private ProductType productType = ProductType.GAME;
    private PaginationHelper paginationHelper;
    private DataModel<Product> items;
    private String keywords;

    public ProductSearchController() {
    }
    
    // Getters and Setters
    
    public ProductType getProductType() {
        return productType;
    }
    
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    
    public PaginationHelper getPaginationHelper() {
        return paginationHelper;
    }
    
    public void setPaginationHelper(PaginationHelper paginationHelper) {
        this.paginationHelper = paginationHelper;
    }
    
    public DataModel<Product> getItems() {
        if (items == null && paginationHelper != null) {
            items = paginationHelper.createPageDataModel();
        }
        
        return items;
    }
    
    public void setItems(DataModel<Product> items) {
        this.items = items;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getProductViewingStr() {
        String result = "";
        if (productType != null) {
            switch (productType) {
                case GAME:
                    result = "game";
                    break;
                case MUSIC: 
                    result = "music";
            }
        }
        
        return result;
    }
    
    // Bussiness functionalities....
       
    public void search(ActionEvent e) {
        switch (productType) {
            case GAME: 
                paginationHelper = new PaginationHelper(10, gameEntryFacade.count(keywords, UserAgentInfo.createInstance())) {
                    
                    @Override
                    public DataModel createPageDataModel() {
                        return new ListDataModel<>(gameEntryFacade.search(keywords,
                                UserAgentInfo.createInstance(), getPageFirstItem(), getPageSize()));
                    }
                };
                break;
            default:
                break;
        }
        items = null;
    }
    
    public void next(ActionEvent e) {
        if (paginationHelper != null) {
            paginationHelper.nextPage();
        }
        items = null;
    }
    
    public void preview(ActionEvent e) {
        if (paginationHelper != null) {
            paginationHelper.previewPage();
        }
        items = null;
    }
    
    //TODO: build 
    public static enum ProductType {
        GAME,MUSIC;
        
        public String getLabel() {
            //TODO: build label product type;
            return null;
        }
        
        @Override
        public String toString() {
            //TODO: build toString product type;
            return null;
        }
    }
}
