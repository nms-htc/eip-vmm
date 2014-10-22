/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.PostCategory;
import com.nms.vnm.eip.service.entity.PostCategoryService;
import javax.ejb.Stateless;

@Stateless
public class PostCategoryServiceBean extends AbstractFacadeBean<PostCategory> implements PostCategoryService {

    private static final long serialVersionUID = 5134502830871578862L;

    public PostCategoryServiceBean() {
        super(PostCategory.class);
    }
}
