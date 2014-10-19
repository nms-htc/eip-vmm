/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vmm.eip.web.controller.admin;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.service.entity.BaseService;
import com.nms.vmm.eip.service.entity.UserService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBean extends AbstractManagedBean<User> {

    private static final long serialVersionUID = 1335928742902659838L;

    @EJB
    private UserService userService;

    @Override
    protected User initEntity() {
        return new User();
    }

    @Override
    protected BaseService<User> getBaseService() {
        return userService;
    }

    public void updatePassword(User user) {
        processEntity(u -> {
            userService.updatePassword(u);
        }, user, REQUEST_SUCCESS_MESSAGE, REQUEST_FAIL_MESSAGE);
    }

    public User.Group[] getUserGroups() {
        return User.Group.values();
    }
}
