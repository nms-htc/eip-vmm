package com.nms.vmm.eip.web.util;


import com.nms.vmm.eip.web.util.JsfUtil;
import java.util.ResourceBundle;


/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
/**
 *
 * @author Cuong
 */
public class MessageUtil {
    
    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }
    
    public static String getBundleMessage(String key) {
        return getResourceBundle().getString(key);
    }
    
    public static void addGlobalSuccessMessage() {
        JsfUtil.addSuccessMessage(getBundleMessage("YourRequestSusscess"));
    }
    
    public static void addGlobalErrorMessage() {
        JsfUtil.addErrorMessage(getBundleMessage("appGlobalErrorMessage"));
    }
    
    public static void addGlobalPersistenceErrorMessage() {
        JsfUtil.addErrorMessage(getBundleMessage("PersistenceErrorOccured"));
    }
    
}
