/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.service.entity.UserService;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Configuration {

//    @EJB
//    private UserService userService;
    
    @PostConstruct
    public void initApplication() {
//        if (!userService.hasAdminUser()) {
//            User user = new User();
//            user.setCode("admin");
//            user.setUsername("admin");
//            user.setEmail("admin@nms.com.vn");
//            user.setPassword("admin");
//            user.setFullname("Administrator");
//            user.setGroups(Arrays.asList(User.Group.Admin));
//            user.setDescription("Default Administrator");
//            userService.persist(user);
//        }
    }
}
