/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Picture;
import com.nms.vnm.eip.entity.PictureCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.PictureService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class PictureServiceBean extends AbstractProductBean<PictureCategory, Picture> implements PictureService {

    private static final long serialVersionUID = -1752966518637682180L;

    public PictureServiceBean() {
        super(Picture.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Picture> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
