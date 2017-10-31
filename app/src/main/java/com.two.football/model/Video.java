package com.two.football.model;

/**
 * Created by TWO on 10/25/2017.
 */

public class Video {
    String id, title, urlThumbnail, urlVideo;

    public Video() {
    }

    public Video(String thumbnail) {
        this.urlThumbnail = thumbnail;
    }

    public Video(String id, String title, String thumbnail, String linkVideo) {
        this.id = id;
        this.title = title;
        this.urlThumbnail = thumbnail;
        this.urlVideo = linkVideo;
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

}
