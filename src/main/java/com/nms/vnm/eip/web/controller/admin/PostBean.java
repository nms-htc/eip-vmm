/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller.admin;

import com.nms.vnm.eip.entity.Post;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PostService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostBean extends AbstractProductBean<Post> {

    private static final long serialVersionUID = 1234824483877206931L;
    @EJB
    private PostService postService;

    @Override
    protected Post initEntity() {
        return new Post();
    }

    @Override
    protected BaseService<Post> getBaseService() {
        return postService;
    }

}
