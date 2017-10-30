package com.two.football.model;

/**
 * Created by TWO on 10/24/2017.
 */

public class Club {

    private String urlLogo;
    private String name;
    private String id;
    private String coach;
    private String year;
    private String stadium;

    public Club() {
    }

    public Club(String clubLogo, String clubName) {
        this.urlLogo = clubLogo;
        this.name = clubName;
    }

    public Club(String urlLogo, String name, String id, String coach, String year, String stadium) {
        this.urlLogo = urlLogo;
        this.name = name;
        this.id = id;
        this.coach = coach;
        this.year = year;
        this.stadium = stadium;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoach() {
        return coach;
    }

    public String getYear() {
        return year;
    }

    public String getStadium() {
        return stadium;
    }
}
