/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vnm.eip.rs;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Cuong
 */
@ApplicationPath("/api")
public class EIPApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EipContentResource.class);
        return classes;
    }
}
