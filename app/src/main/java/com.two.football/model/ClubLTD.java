package com.two.football.model;

/**
 * Created by TWO on 11/1/2017.
 */

public class ClubLTD {
    private String time, tournament, guestClub, homeClub, logoHome, logoGuest, round;
    public ClubLTD() {
    }

    public ClubLTD(String time, String date, String guestClub, String homeClub, String logoHome, String logoGuest, String round) {

        this.time = time;
        this.tournament = date;
        this.guestClub = guestClub;
        this.homeClub = homeClub;
        this.logoHome = logoHome;
        this.logoGuest = logoGuest;
        this.round = round;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
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

    public String getTournament() {
        return tournament;
    }

    public String getGuestClub() {
        return guestClub;
    }

    public String getHomeClub() {
        return homeClub;
    }
}
