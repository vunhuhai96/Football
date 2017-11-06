package com.two.football.model;

/**
 * Created by TWO on 10/23/2017.
 */

public class Highlight {

    String id, title, urlThumbnail, urlVideo, videoType, tournaments, key;

    public Highlight() {
    }

    public Highlight(String thumbnail) {
        this.urlThumbnail = thumbnail;
    }

    public Highlight(String id, String title, String urlThumbnail, String urlVideo, String videoType, String tournaments) {
        this.id = id;
        this.title = title;
        this.urlThumbnail = urlThumbnail;
        this.urlVideo = urlVideo;
        this.videoType = videoType;
        this.tournaments = tournaments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getTournaments() {
        return tournaments;
    }

    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }

    public String getVideoType() {
        return videoType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
