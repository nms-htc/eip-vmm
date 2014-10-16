/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.AppServiceClient;
import com.nms.vmm.eip.ejb.GameCategoryFacade;
import com.nms.vmm.eip.ejb.GameEntryFacade;
import com.nms.vmm.eip.entity.Flatform;
import com.nms.vmm.eip.entity.GameCategory;
import com.nms.vmm.eip.entity.Game;
import com.nms.vmm.eip.web.util.JsfUtil;
import com.nms.vmm.eip.web.util.MessageUtil;
import com.nms.vmm.eip.web.util.PaginationHelper;
import com.nms.vmm.eip.web.util.UserAgentInfo;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Cuong
 */
@Named
@ViewScoped
public class GameDetailController implements Serializable {

    private static final long serialVersionUID = 4146463756712373608L;
    private Game gameEntry;
    private DataModel<Game> gameEntries;
    private PaginationHelper paginationHelper;
    private GameCategory gameCategory = null;
    @EJB
    private GameEntryFacade facade;
    @EJB
    private GameCategoryFacade categoryFacade;
    @EJB
    private AppServiceClient serviceClient;

    public GameDetailController() {
    }

    public void loadProduct() {
        String id = JsfUtil.getRequestParameter("id");
        Long pkKey = null;
        try {
            pkKey = Long.parseLong(id);
        } catch (NumberFormatException e) {
        }

        if (pkKey != null) {
            gameEntry = facade.find(pkKey);
        }
    }
    
    public void reloadList() {
        paginationHelper = null;
        gameCategory = null;
        gameEntries = null;
    }
    
    public Game getGameEntry() {

        return gameEntry;
    }

    public void setGameEntry(Game gameEntry) {
        this.gameEntry = gameEntry;
    }

    public DataModel<Game> getGameEntries() {
        if (gameEntries == null) {
            gameEntries = getPaginationHelper().createPageDataModel();
        }
        return gameEntries;
    }

    public void setGameEntries(DataModel<Game> gameEntries) {
        this.gameEntries = gameEntries;
    }

    public GameCategory getGameCategory() {
        if (gameCategory == null) {
            Long categoryId = getCategoryId();
            if (categoryId != null) {
                gameCategory = categoryFacade.find(categoryId);
            }
        }
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public String buyGame() {

        try {

            String isdn = serviceClient.checkPhoneNumber();
            if (isdn != null && !isdn.trim().isEmpty()) {
                String osCode;
                UserAgentInfo agentInfo = UserAgentInfo.createInstance();
                
                if (agentInfo.detectIos()) {
                    osCode = String.valueOf(Flatform.IOS.ordinal() + 1);
                } else if (agentInfo.detectAndroid()) {
                    osCode = String.valueOf(Flatform.ANDROID.ordinal() + 1);
                } else if (agentInfo.detectWindowsMobile()) {
                    osCode = String.valueOf(Flatform.WINDOW_PHONE.ordinal() + 1);
                } else {
                    osCode = String.valueOf(Flatform.JAVA.ordinal() + 1);
                }
                
                boolean success = serviceClient.charging(gameEntry.getCode(), gameEntry.getCpCode(), isdn,
                        gameEntry.getPrice(),
                        MessageUtil.getBundleMessage("Service.NmsChargingGame.Shortcode"), osCode);

                if (success) {
                    // update download count
                    gameEntry.setDownloadCount(gameEntry.getDownloadCount() + 1);
                    facade.edit(gameEntry);

                // logger buy game... 
                    MessageUtil.addGlobalSuccessMessage();
                } else {
                    JsfUtil.addErrorMessage(MessageUtil.getBundleMessage("CharginErrorMessage"));
                }
            } else {
                JsfUtil.addErrorMessage(MessageUtil.getBundleMessage("CanNotDetectISDNErrorMessage"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e.getMessage());
            MessageUtil.addGlobalErrorMessage();
            throw e;

        }

        return null;
    }

    public PaginationHelper getPaginationHelper() {
        
        if (paginationHelper == null) {
            paginationHelper = new PaginationHelper(10, facade.countByCategory(getCategoryId(), 
                    UserAgentInfo.createInstance())) {
                
                @Override
                public DataModel createPageDataModel() {
                    Long id = null;
                    if (getGameCategory() != null) {
                        id = gameCategory.getId();
                    }
                    return new ListDataModel(facade.findByCategory(id, 
                            UserAgentInfo.createInstance(),
                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize() - 1},
                            "createdDate", "desc"));
                }
            };
        }
        
        return paginationHelper;
    }

    public void setPaginationHelper(PaginationHelper paginationHelper) {
        this.paginationHelper = paginationHelper;
    }
    
    private Long getCategoryId() {
        String catId = JsfUtil.getRequestParameter("catId");
        Long categoryId;
        try {
            categoryId = Long.parseLong(catId);
        } catch (NumberFormatException e) {
            categoryId = null;
        }
        return categoryId;
    }
    
    public String next() {
        paginationHelper.nextPage();
        gameEntries = null;
        return null;
    }
    
    public String preview() {
        paginationHelper.previewPage();
        gameEntries = null;
        return null;
    }
}
