package com.two.football.model;

/**
 * Created by TWO on 11/1/2017.
 */

public class LTD {
    private String time, date, guestClub, homeClub, logoHome, logoGuest, result, round;
    public LTD() {
    }

    public LTD(String time, String date, String guestClub, String homeClub, String logoHome, String logoGuest, String result, String round) {

        this.time = time;
        this.date = date;
        this.guestClub = guestClub;
        this.homeClub = homeClub;
        this.logoHome = logoHome;
        this.logoGuest = logoGuest;
        this.result = result;
        this.round = round;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
