/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vmm.eip.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cuong
 */
@Entity
@XmlRootElement
public class GameCategory extends Category {

    private static final long serialVersionUID = -8167109820678750424L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<GameEntry> gameEntries;

    @Override
    public String toString() {
        if (title != null && title.length() > 0) {
            return title;
        }
        return "com.nms.vmm.eip.entity.GameCategory[ id=" + id + " ]";
    }

    @XmlTransient
    public List<GameEntry> getGameEntries() {
        return gameEntries;
    }

    public void setGameEntries(List<GameEntry> gameEntries) {
        this.gameEntries = gameEntries;
    }
}
