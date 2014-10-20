/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.web.controller.admin;

import com.nms.vmm.eip.entity.User;
import com.nms.vmm.eip.service.entity.BaseService;
import com.nms.vmm.eip.service.entity.UserService;
import com.nms.vmm.eip.web.util.JsfUtil;
import com.nms.vmm.eip.web.util.MessageUtil;
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

    public void updatePassword() {
        JsfUtil.processAction(u -> {
            userService.updatePassword(u);
        }, current, MessageUtil.REQUEST_SUCCESS_MESSAGE, MessageUtil.REQUEST_FAIL_MESSAGE);
    }

    public User.Group[] getUserGroups() {
        return User.Group.values();
    }
}
