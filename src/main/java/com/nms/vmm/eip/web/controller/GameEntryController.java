/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller;

import com.nms.vmm.eip.ejb.GameEntryFacade;
import com.nms.vmm.eip.entity.Flatform;
import com.nms.vmm.eip.entity.GameEntry;
import com.nms.vmm.eip.entity.UserEntry;
import com.nms.vmm.eip.web.util.MessageUtil;
import com.nms.vmm.eip.web.util.PaginationHelper;
import com.nms.vmm.eip.web.util.UserAgentInfo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Cuong
 */
@Named
@SessionScoped
public class GameEntryController implements Serializable {

    private static final long serialVersionUID = -1097883751800242866L;

    // Private Data Memmber
    @EJB
    private GameEntryFacade facade;
    private GameEntry current;
    private DataModel<GameEntry> items;
    private PaginationHelper paginationHelper;
    @Inject
    private UserEntryController userEntryController;

    // Contructors.
    public GameEntryController() {
    }

    // Getters and Setters
    public GameEntry getCurrent() {
        if (current == null) {
            current = new GameEntry();
        }
        return current;
    }

    public void setCurrent(GameEntry current) {
        this.current = current;
    }

    public DataModel<GameEntry> getItems() {
        if (items == null) {
            items = getPaginationHelper().createPageDataModel();
        }
        return items;
    }

    public void setItems(DataModel<GameEntry> items) {
        this.items = items;
    }

    public PaginationHelper getPaginationHelper() {
        if (paginationHelper == null) {
            paginationHelper = new PaginationHelper(10, facade.countAll()) {

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(facade.findRange(
                            new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize() -1})
                    );
                }
            };
        }
        return paginationHelper;
    }

    public void setPaginationHelper(PaginationHelper paginationHelper) {
        this.paginationHelper = paginationHelper;
    }

    public String prepareCreate() {
        current = null;
        return "create";
    }

    public String prepareEdit() {
        current = items.getRowData();
        return "edit";
    }

    public String create() {
        try {
            facade.create(current, getCurrentCpCode());
            MessageUtil.addGlobalSuccessMessage();
            items = null;
            paginationHelper = null;
            return "list";
        } catch (Exception e) {
            MessageUtil.addGlobalPersistenceErrorMessage();
            return null;
        }
    }

    public String update() {
        try {
            facade.edit(current, getCurrentCpCode());
            MessageUtil.addGlobalSuccessMessage();
            items = null;
            paginationHelper = null;
            return "list";
        } catch (Exception e) {
            MessageUtil.addGlobalPersistenceErrorMessage();
            return null;
        }
    }

    public String delete() {
        current = items.getRowData();
        try {
            facade.remove(current);
            MessageUtil.addGlobalSuccessMessage();
            items = null;
            paginationHelper = null;
            return "list";
        } catch (Exception e) {
            MessageUtil.addGlobalPersistenceErrorMessage();
            return null;
        }
    }

    public List<GameEntry> getTopDownloadGames() {
        return facade.findByCategory(null, UserAgentInfo.createInstance(), new int[] {0, 9}, "downloadCount", "desc");
    }

    public List<GameEntry> getHotGames() {
        return facade.findByCategory(null, UserAgentInfo.createInstance(), new int[] {0, 9}, "downloadCount", "desc");
    }

    public List<GameEntry> getNewGames() {
        return facade.findByCategory(null, UserAgentInfo.createInstance(), new int[] {0, 9}, "createdDate", "desc");
    }

    public SelectItem[] getFlatformSelectItems() {
        SelectItem[] selectItems = new SelectItem[Flatform.values().length];

        for (int i = 0; i < Flatform.values().length; i++) {
            Flatform flatform = Flatform.values()[i];
            selectItems[i] = (new SelectItem(flatform, flatform.toLabel()));
        }

        return selectItems;
    }

    private String getCurrentCpCode() {
        String cpCode = null;
        UserEntry userEntry = userEntryController.getUserFromRequest();
        if (userEntry != null) {
            cpCode = userEntry.getCode();
        }
        return cpCode;
    }

    public String nextPage() {
        paginationHelper.nextPage();
        items = null;
        return "list?faces-redirect=true";
    }

    public String previewPage() {
        paginationHelper.previewPage();
        items = null;
        return "list?faces-redirect=true";
    }

    public String startPage() {
        paginationHelper.goToStartPage();
        items = null;
        return "list?faces-redirect=true";
    }
    
    public String endPage() {
        paginationHelper.goToEndPage();
        items = null;
        return "list?faces-redirect=true";
    }
}
