/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Video;
import com.nms.vnm.eip.entity.VideoCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.VideoService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class VideoServiceBean extends AbstractProductBean<VideoCategory, Video> implements VideoService {

    private static final long serialVersionUID = -1210801531435011082L;

    public VideoServiceBean() {
        super(Video.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Video> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
