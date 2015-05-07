/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.ejb;

import com.nms.vnm.eip.entity.Game;
import com.nms.vnm.eip.entity.GameCategory;
import com.nms.vnm.eip.entity.Game_;
import com.nms.vnm.eip.search.OrderType;
import com.nms.vnm.eip.service.MobileChecker;
import com.nms.vnm.eip.service.entity.GameService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class GameServiceBean extends AbstractProductBean<GameCategory, Game> implements GameService {

    private static final long serialVersionUID = 4734878632483200574L;

    public GameServiceBean() {
        super(Game.class);
    }

    @Override
    protected List<Predicate> buildPredicates(CriteriaBuilder cd, Root<Game> root, MobileChecker mobileChecker) {

        List<Predicate> list = new ArrayList<>();

        if (mobileChecker.isMobile()) {
            if (mobileChecker.isAndroid()) {
                list.add(cd.isMember(Game.Flatform.Android, root.get(Game_.flatforms)));
            } else if (mobileChecker.isIos()) {
                list.add(cd.isMember(Game.Flatform.Ios, root.get(Game_.flatforms)));
            } else if (mobileChecker.isWindowPhone()) {
                list.add(cd.isMember(Game.Flatform.Window_Phone, root.get(Game_.flatforms)));
            } else {
                list.add(cd.isMember(Game.Flatform.Java, root.get(Game_.flatforms)));
            }
        }

        return list;
    }

    @Override
    public List<Game> search(Long categoryId, String keywords, OrderType orderType, int page, int range, Game.Flatform flatform) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        Root<Game> root = cq.from(Game.class);
        cq.select(root);

        GameCategory category = null;

        if (categoryId != null) {
            try {
                category = em.find(GameCategory.class, categoryId);
            } catch (Exception e) {
            }
        }

        List<Predicate> predicates = buildPredicates(cb, root, category, keywords);

        if (flatform != null) {
            predicates.add(cb.isMember(flatform, root.get(Game_.flatforms)));
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        if (orderType != null) {
            switch (orderType) {
                case TOP_DOWNLOAD:
                    cq.orderBy(cb.desc(root.get(Game_.downloadCount)));
                    break;
                case TOP_HOT:
                    cq.orderBy(cb.desc(root.get(Game_.viewCount)));
                    break;
                case TOP_NEW:
                    cq.orderBy(cb.desc(root.get(Game_.createdDate)));
                    break;
                case TOP_VIEW:
                    cq.orderBy(cb.desc(root.get(Game_.viewCount)));
                    break;
                default:
                    break;

            }
        }
        
        if (page < 0) page = 0;
        if (range <= 0) range = 10;

        TypedQuery<Game> q = em.createQuery(cq);
        q.setFirstResult(page * range);
        q.setMaxResults(range);
        return q.getResultList();
    }
}
