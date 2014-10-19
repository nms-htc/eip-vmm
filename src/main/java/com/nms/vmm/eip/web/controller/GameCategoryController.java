///**
// * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
// */
//package com.nms.vmm.eip.web.controller;
//
//import com.nms.vmm.eip.ejb.GameCategoryFacade;
//import com.nms.vmm.eip.ejb.GameEntryFacade;
//import com.nms.vmm.eip.entity.GameCategory;
//import com.nms.vmm.eip.entity.Game;
//import com.nms.vmm.eip.web.util.JsfUtil;
//import com.nms.vmm.eip.web.util.MessageUtil;
//import com.nms.vmm.eip.web.util.PaginationHelper;
//import java.io.Serializable;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//
///**
// *
// * @author Cuong
// */
//@Named
//@SessionScoped
//public class GameCategoryController implements Serializable {
//
//    private static final long serialVersionUID = -6755123707621626963L;
//
//    @EJB
//    private GameCategoryFacade gameCategoryFacade;
//    private GameCategory current;
//    private DataModel items;
//    private PaginationHelper paginationHelper;
//    @EJB
//    private GameEntryFacade gameEntryFacade;
//
//    public GameCategoryController() {
//    }
//
//    public GameCategory getCurrent() {
//
//        if (current == null) {
//            current = new GameCategory();
//        }
//        return current;
//    }
//
//    public void setCurrent(GameCategory current) {
//        this.current = current;
//    }
//
//    public DataModel getItems() {
//        if (items == null) {
//            items = getPaginationHelper().createPageDataModel();
//        }
//        return items;
//    }
//
//    public void setItems(DataModel items) {
//        this.items = items;
//    }
//
//    public PaginationHelper getPaginationHelper() {
//
//        if (paginationHelper == null) {
//            paginationHelper = new PaginationHelper(10, gameCategoryFacade.countAll()) {
//
//                @Override
//                public DataModel createPageDataModel() {
//                    return new ListDataModel(gameCategoryFacade
//                            .findRange(new int[]{getPageFirstItem(),
//                                getPageFirstItem() + getPageSize() - 1}));
//                }
//            };
//        }
//
//        return paginationHelper;
//    }
//
//    public void setPaginationHelper(PaginationHelper paginationHelper) {
//        this.paginationHelper = paginationHelper;
//    }
//
//    public String prepareCreate() {
//        current = null;
//        return "create";
//    }
//
//    public String create() {
//        try {
//            gameCategoryFacade.create(current);
//            items = null;
//            MessageUtil.addGlobalSuccessMessage();
//            return "list";
//        } catch (Exception e) {
//            MessageUtil.addGlobalPersistenceErrorMessage();
//            return null;
//        }
//    }
//
//    public String prepareEdit() {
//        current = (GameCategory) items.getRowData();
//        return "edit";
//    }
//
//    public String update() {
//        try {
//            gameCategoryFacade.edit(current);
//            MessageUtil.addGlobalSuccessMessage();
//            return "list";
//        } catch (Exception e) {
//            MessageUtil.addGlobalPersistenceErrorMessage();
//            return null;
//        }
//    }
//
//    @SuppressWarnings("ThrowableResultIgnored")
//    public String delete() {
//        current = (GameCategory) items.getRowData();
//        try {
//            List<Game> games = gameEntryFacade.findByCategoryId(current.getId());
//            if (games != null && games.size() > 0) {
//                JsfUtil.addErrorMessage(MessageUtil.getBundleMessage("GameCategoryDeleteCategoryHasGameErrorMessage"));
//            } else {
//                gameCategoryFacade.remove(current);
//                MessageUtil.addGlobalSuccessMessage();
//            }
//            current = null;
//            paginationHelper = null;
//            items = null;
//            return "list";
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage(JsfUtil.getRootCause(e).getLocalizedMessage());
//            return null;
//        }
//    }
//
//    public SelectItem[] getItemsAvailableSelectMany() {
//        return JsfUtil.getSelectItems(gameCategoryFacade.findAll(), false);
//    }
//
//    public SelectItem[] getItemsAvailableSelectOne() {
//        return JsfUtil.getSelectItems(gameCategoryFacade.findAll(), true);
//    }
//
//    public GameCategory getCategory(Long id) {
//        return gameCategoryFacade.find(id);
//    }
//
//    @FacesConverter(forClass = GameCategory.class)
//    public static class GameCategoryControllerConvertor implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext context, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//
//            GameCategoryController controller = (GameCategoryController) context.getApplication().getELResolver()
//                    .getValue(context.getELContext(), null, "gameCategoryController");
//
//            return controller.getCategory(getKey(value));
//        }
//
//        private Long getKey(String value) {
//            return Long.valueOf(value);
//        }
//
//        private String getStringKey(Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext context, UIComponent component, Object value) {
//            if (value == null) {
//                return null;
//            }
//
//            if (value instanceof GameCategory) {
//                GameCategory category = (GameCategory) value;
//                return getStringKey(category.getId());
//            } else {
//                throw new IllegalArgumentException("object "
//                        + value + " is of type " + value.getClass().getName()
//                        + "; expected type: " + GameCategory.class.getName());
//            }
//        }
//    }
//
//    public String nextPage() {
//        paginationHelper.nextPage();
//        items = null;
//        return "list?faces-redirect=true";
//    }
//
//    public String previewPage() {
//        paginationHelper.previewPage();
//        items = null;
//        return "list?faces-redirect=true";
//    }
//
//    public String startPage() {
//        paginationHelper.goToStartPage();
//        items = null;
//        return "list?faces-redirect=true";
//    }
//
//    public String endPage() {
//        paginationHelper.goToEndPage();
//        items = null;
//        return "list?faces-redirect=true";
//    }
//}
