package com.two.football.model;

/**
 * Created by TWO on 10/25/2017.
 */

public class Video {
    String id,title,thumbnail,linkVideo
            ;

    public Video() {
    }

    public Video(String id, String title, String thumbnail, String linkVideo) {
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
}
