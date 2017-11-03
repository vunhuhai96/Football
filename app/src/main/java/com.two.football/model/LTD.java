package com.two.football.model;

/**
 * Created by TWO on 11/1/2017.
 */

public class LTD {
    private String time, date, guestClub, homeClub, logoHome, logoGuest;
    public LTD() {
    }

    public LTD(String time, String date, String guestClub, String homeClub, String logoHome, String logoGuest) {
        this.time = time;
        this.date = date;
        this.guestClub = guestClub;
        this.homeClub = homeClub;
        this.logoHome = logoHome;
        this.logoGuest = logoGuest;
    }

    public LTD(String time, String date, String guestClub, String homeClub) {
        this.time = time;
        this.date = date;
        this.guestClub = guestClub;
        this.homeClub = homeClub;
    }

    public String getLogoHome() {
        return logoHome;
    }

    public String getLogoGuest() {
        return logoGuest;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getGuestClub() {
        return guestClub;
    }

    public String getHomeClub() {
        return homeClub;
    }
}
