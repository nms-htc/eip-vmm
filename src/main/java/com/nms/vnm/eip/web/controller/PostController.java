/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.controller;

import com.nms.vnm.eip.entity.Post;
import com.nms.vnm.eip.entity.PostCategory;
import com.nms.vnm.eip.service.entity.PostService;
import com.nms.vnm.eip.service.entity.ProductService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostController extends AbstractProductController<Post, PostCategory> {

    private static final long serialVersionUID = -4276685476416044041L;

    @EJB
    private PostService postService;

    @Override
    protected ProductService<Post, PostCategory> getProductService() {
        return postService;
    }

}
