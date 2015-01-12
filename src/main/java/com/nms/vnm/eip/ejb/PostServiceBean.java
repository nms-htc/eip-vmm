/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Post;
import com.nms.vnm.eip.entity.PostCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.PostService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class PostServiceBean extends AbstractProductBean<PostCategory, Post> implements PostService {

    private static final long serialVersionUID = -857383942536246354L;

    public PostServiceBean() {
        super(Post.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Post> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
