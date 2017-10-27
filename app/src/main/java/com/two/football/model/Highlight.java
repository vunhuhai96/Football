package com.two.football.model;

/**
 * Created by TWO on 10/23/2017.
 */

public class Highlight {

    String id, title, thumbnail, link;

    public Highlight() {
    }

    public Highlight(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Highlight(String id, String title, String thumbnail, String link) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.link = link;
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

    public String getLink() {
        return link;
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

    public void setLinkVideo(String link) {
        this.link = link;
    }
}
