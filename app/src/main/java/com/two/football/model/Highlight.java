package com.two.football.model;

/**
 * Created by TWO on 10/23/2017.
 */

public class Highlight {

    String id, title, urlThumbnail, urlVideo, videoType;

    public Highlight() {
    }

    public Highlight(String thumbnail) {
        this.urlThumbnail = thumbnail;
    }

    public Highlight(String id, String title, String thumbnail, String link) {
        this.id = id;
        this.title = title;
        this.urlThumbnail = thumbnail;
        this.urlVideo = link;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public void setLinkVideo(String link) {
        this.urlVideo = link;
    }

    public String getVideoType() {
        return videoType;
    }
}
