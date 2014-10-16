/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.entity.UserRole;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Configuration {

    @EJB
    private UserEntryFacade userService;
    
    @PostConstruct
    public void initApplication() {
        if (userService.countAdministrator() <= 0) {
            User userEntry = new User();
            userEntry.setCode("admin");
            userEntry.setDescription("Default Administrator");
            userEntry.setEmail("admin@nms.com.vn");
            userEntry.setPassword("admin");
            userEntry.setFullName("Administrator");
            userEntry.setUserRoles(Arrays.asList(UserRole.Admin));
            userService.create(userEntry);
        }
    }
}
