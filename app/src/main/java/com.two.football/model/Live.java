package com.two.football.model;

/**
 * Created by TWO on 10/23/2017.
 */

public class Live {

    private String title, guestClub, homeClub, urlVideo, videoType;

    public Live(){

    }

    public Live(String title, String guestClub, String homeClub, String urlVideo, String videoType) {
        this.title = title;
        this.guestClub = guestClub;
        this.homeClub = homeClub;
        this.urlVideo = urlVideo;
        this.videoType = videoType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuestClub() {
        return guestClub;
    }

    public void setGuestClub(String guestClub) {
        this.guestClub = guestClub;
    }

    public String getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(String homeClub) {
        this.homeClub = homeClub;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}
