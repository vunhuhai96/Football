package com.two.football.model;

/**
 * Created by TWO on 10/24/2017.
 */

public class Club {

    private String clubLogo;
    private String clubName;
    private String id;

    public Club() {
    }

    public Club(String clubLogo, String clubName) {
        this.clubLogo = clubLogo;
        this.clubName = clubName;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public String getClubName() {
        return clubName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
