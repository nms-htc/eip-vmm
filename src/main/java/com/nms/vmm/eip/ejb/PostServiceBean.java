/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.ejb;

import com.nms.vmm.eip.entity.Post;
import com.nms.vmm.eip.service.entity.PostService;
import javax.ejb.Stateless;

@Stateless
public class PostServiceBean extends AbstractFacadeBean<Post> implements PostService {

    private static final long serialVersionUID = -857383942536246354L;

    public PostServiceBean() {
        super(Post.class);
    }

}
