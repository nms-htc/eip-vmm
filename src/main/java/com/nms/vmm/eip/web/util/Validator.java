/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.util;

/**
 *
 * @author Cuong
 */
public class Validator {
    public static boolean isNull(Object object) {
        return object == null;
    }
    
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }
    
    public static boolean isNull(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    public static boolean isNotNull(String value) {
        return !isNull(value);
    }
}
