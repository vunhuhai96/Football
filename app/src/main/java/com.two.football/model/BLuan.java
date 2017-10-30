package com.two.football.model;


public class BLuan {
    private int Icon;
    private String name1;
    private String content;

    public BLuan(int icon, String name1, String content) {
        Icon = icon;
        this.name1 = name1;
        this.content = content;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
