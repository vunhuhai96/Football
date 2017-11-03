package com.two.football.model;

public class BLuan {
    private String  idUser, name, avatar, content, time, titleVideo;

    public BLuan() {
    }



    public BLuan( String idUser, String name, String avatar, String content, String time, String titleVideo) {

        this.idUser = idUser;
        this.name = name;
        this.avatar = avatar;
        this.content = content;
        this.time = time;
        this.titleVideo = titleVideo;


    }



    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitleVideo() {
        return titleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        this.titleVideo = titleVideo;
    }



}