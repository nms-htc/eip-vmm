/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.entity;

import com.nms.vmm.eip.web.util.MessageUtil;

/**
 *
 * @author Cuong
 */
public enum Flatform {

    ANDROID, IOS, JAVA, WINDOW_PHONE, OTHER;
    
    public static Flatform fromInteger(int intValue) {
        Flatform result = OTHER;
        switch (intValue) {
            case 1: 
                result = ANDROID;
                break;
            case 2:
                result = IOS;
                break;
            case 3:
                result = JAVA;
                break;
            case 4:
                result = WINDOW_PHONE;
                break;
            case 5:
                result = OTHER;
                break;
        }
        
        return result;
    }

    public String toLabel() {
        String strValue = MessageUtil.getBundleMessage("FlatForm.Other");

        switch (this) {
            case ANDROID:
                strValue = MessageUtil.getBundleMessage("FlatForm.Android");
                break;
            case IOS:
                strValue = MessageUtil.getBundleMessage("FlatForm.IOS");
                break;
            case JAVA:
                strValue = MessageUtil.getBundleMessage("FlatForm.Java");
                break;

            case WINDOW_PHONE:
                strValue = MessageUtil.getBundleMessage("FlatForm.WindowPhone");
                break;
        }

        return strValue;
    }
}
