package com.two.football.model;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoFavorite {
    String id, title, thumbnail, linkVideo;

    public VideoFavorite() {
    }

    public VideoFavorite(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public VideoFavorite(String id, String title, String thumbnail, String linkVideo) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.linkVideo = linkVideo;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
