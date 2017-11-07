package com.two.football.model;

/**
 * Created by ADMIN on 11/1/2017.
 */

public class Reserves {

    private String id, title, link, tournaments, nameServer;

    public Reserves(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reserves(String id, String title, String link, String tournaments, String nameServer) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.tournaments = tournaments;
        this.nameServer = nameServer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTournaments() {
        return tournaments;
    }

    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }
}
