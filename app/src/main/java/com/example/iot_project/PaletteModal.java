package com.example.iot_project;

import android.graphics.Color;

public class PaletteModal {
    private String hexOriginal;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private int liked;

    public PaletteModal() {
    }

    public PaletteModal(String hex, String color1, String color2, String color3, String color4, int liked) {
        this.hexOriginal = hex;
        this.c1 = color1;
        this.c2 = color2;
        this.c3 = color3;
        this.c4 = color4;
        this.liked = liked;
    }

    public String getHexOriginal() {
        return hexOriginal;
    }

    public void setHexOriginal(String hexOriginal) {
        this.hexOriginal = hexOriginal;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public int getColorHexToInt(String color){
        return Color.parseColor(color);
    }

    public int getLiked(){
        return liked;
    }
}
