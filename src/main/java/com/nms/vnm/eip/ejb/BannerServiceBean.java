/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Banner;
import com.nms.vnm.eip.entity.BannerCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.BannerService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class BannerServiceBean extends AbstractProductBean<BannerCategory, Banner> implements BannerService {

    private static final long serialVersionUID = -1752966518637682180L;

    public BannerServiceBean() {
        super(Banner.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Banner> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
