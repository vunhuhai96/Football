package com.two.football.model;

/**
 * Created by ADMIN on 11/1/2017.
 */

public class Reserves {

    private String title, tournaments, urlReserves;

    public Reserves(){

    }

    public Reserves(String urlReserves) {
        this.urlReserves = urlReserves;
    }

    public Reserves(String title, String tournaments, String urlReserves) {
        this.title = title;
        this.tournaments = tournaments;
        this.urlReserves = urlReserves;
    }

    public String getUrlReserves() {
        return urlReserves;
    }

    public void setUrlReserves(String urlReserves) {
        this.urlReserves = urlReserves;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTournaments() {
        return tournaments;
    }

    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }
}
