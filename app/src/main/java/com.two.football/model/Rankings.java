package com.two.football.model;

import android.widget.ImageView;

/**
 * Created by ADMIN on 10/31/2017.
 */

public class Rankings {

    private String table;
    private String teamImg;
    private String nameTeam;
    private String battle;
    private String win;
    private String draw;
    private String lose;
    private String offset;
    private String totalPoints;

    public Rankings(){

    }

    public Rankings(String table) {
        this.table = table;
    }

    public Rankings(String nameTeam, String battle, String win, String draw, String lose, String offset, String totalPoints) {
        this.nameTeam = nameTeam;
        this.battle = battle;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.offset = offset;
        this.totalPoints = totalPoints;
    }

    //    public Results(String table, ImageView teamImg, String nameTeam, String battle, String win, String draw, String lose, String offset, String totalPoints) {
//        this.table = table;
//        this.teamImg = teamImg;
//        this.nameTeam = nameTeam;
//        this.battle = battle;
//        this.win = win;
//        this.draw = draw;
//        this.lose = lose;
//        this.offset = offset;
//        this.totalPoints = totalPoints;
//    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTeamImg() {
        return teamImg;
    }

    public void setTeamImg(String teamImg) {
        this.teamImg = teamImg;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getBattle() {
        return battle;
    }

    public void setBattle(String battle) {
        this.battle = battle;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
}
