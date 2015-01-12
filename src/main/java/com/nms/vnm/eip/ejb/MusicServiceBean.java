/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.Music;
import com.nms.vnm.eip.entity.MusicCategory;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.MusicService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class MusicServiceBean extends AbstractProductBean<MusicCategory, Music> implements MusicService {

    private static final long serialVersionUID = 4317161615998592675L;

    public MusicServiceBean() {
        super(Music.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Music> root, MobileChecker mobileChecker) {
        return new LinkedList<>();
    }

}
