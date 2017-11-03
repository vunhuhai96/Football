package com.two.football.model;

/**
 * Created by TWO on 10/25/2017.
 */

public class Video {
    String id, title, urlThumbnail, urlVideo,tournaments;

    public Video() {
    }

    public Video(String id, String title, String urlThumbnail, String urlVideo, String tournaments) {
        this.id = id;
        this.title = title;
        this.urlThumbnail = urlThumbnail;
        this.urlVideo = urlVideo;
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

    public String getTournament() {
        return tournaments;
    }

    public void setTournament(String tournament) {
        this.tournaments = tournament;
    }
}
