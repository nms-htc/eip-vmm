/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights
 * reserved.
 */
package com.nms.vnm.eip.entity;

import com.nms.vnm.eip.entity.validation.Url;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("Music")
@XmlRootElement
public class Music extends Product<MusicCategory> {

    private static final long serialVersionUID = 5149591841140789090L;

    @Url
    @Size(max = 500)
    @Column(name = "MUSIC_URL", length = 500)
    protected String musicUrl;

    @Url
    @Size(max = 500)
    @Column(name = "WAIT_MUSIC_URL", length = 500)
    protected String waitMusicUrl;

    @Size(max = 150)
    @Column(name = "SINGER", length = 150)
    protected String singer;

    @Size(max = 150)
    @Column(name = "CREATOR", length = 150)
    protected String creator;

    @Size(max = 150)
    @Column(name = "ALBUM", length = 150)
    protected String album;

    @Url
    @Size(max = 500)
    @Column(name = "ALBUM_THUMB_URL", length = 500)
    protected String albumThumbUrl;

    @Size(max = 2000)
    @Column(name = "LYRIC", length = 2000)
    protected String lyric;

    public Music() {
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getWaitMusicUrl() {
        return waitMusicUrl;
    }

    public void setWaitMusicUrl(String waitMusicUrl) {
        this.waitMusicUrl = waitMusicUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumThumbUrl() {
        return albumThumbUrl;
    }

    public void setAlbumThumbUrl(String albumThumbUrl) {
        this.albumThumbUrl = albumThumbUrl;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
