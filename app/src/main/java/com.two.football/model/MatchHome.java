package com.two.football.model;

/**
 * Created by phamthanhcong on 02/11/2017.
 */

public class MatchHome {
    String homeClub, guestClub, logoHome, logoGuest, tournament, time, date;

    public MatchHome() {
    }

    public MatchHome(String homeClub, String guestClub, String logoHome, String logoGuest, String tournament, String time) {
        this.homeClub = homeClub;
        this.guestClub = guestClub;
        this.logoHome = logoHome;
        this.logoGuest = logoGuest;
        this.tournament = tournament;
        this.time = time;
    }

    public String getHomeClub() {
        return homeClub;
    }

    public String getGuestClub() {
        return guestClub;
    }

    public String getLogoHome() {
        return logoHome;
    }

    public String getLogoGuest() {
        return logoGuest;
    }

    public String getTournament() {
        return tournament;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
