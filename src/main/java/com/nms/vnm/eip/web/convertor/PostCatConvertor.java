/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.web.convertor;

import com.nms.vnm.eip.entity.PostCategory;
import com.nms.vnm.eip.service.entity.BaseService;
import com.nms.vnm.eip.service.entity.PostCategoryService;
import javax.ejb.EJB;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = PostCategory.class)
public class PostCatConvertor extends AbstractEntityConvertor<PostCategory> {

    @EJB
    private PostCategoryService postCatService;

    @Override
    protected BaseService<PostCategory> getBaseService() {
        return postCatService;
    }

}
