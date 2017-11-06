package com.two.football.model;

import android.widget.ImageView;

/**
 * Created by ADMIN on 10/31/2017.
 */

public class Rankings {

    private String stt;
    private String teamImg;
    private String teamName;
    private String battle;
    private String win;
    private String draw;
    private String lose;
    private String offset;
    private String totalPoints;

    public Rankings(){

    }

    public Rankings(String stt, String teamName, String battle, String win, String draw, String lose, String offset, String totalPoints) {
        this.stt = stt;
//        this.teamImg = teamImg;
        this.teamName = teamName;
        this.battle = battle;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.offset = offset;
        this.totalPoints = totalPoints;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTeamImg() {
        return teamImg;
    }

    public void setTeamImg(String teamImg) {
        this.teamImg = teamImg;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
