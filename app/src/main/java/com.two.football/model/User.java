package com.two.football.model;

/**
 * Created by phamthanhcong on 01/11/2017.
 */

public class User {
    String name,id,urlAvatar;

    public User() {
    }

    public User(String name, String id, String urlAvatar) {
        this.name = name;
        this.id = id;
        this.urlAvatar = urlAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
}
