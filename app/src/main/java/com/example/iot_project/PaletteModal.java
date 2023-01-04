package com.example.iot_project;

import android.graphics.Color;

public class PaletteModal {
    private String hexOriginal;
    private String c1;
    private String c2;
    private String c3;
    private String c4;

    public PaletteModal() {
    }

    public PaletteModal(String hex, String color1, String color2, String color3, String color4) {
        hexOriginal = hex;
        c1 = color1;
        c2 = color2;
        c3 = color3;
        c4 = color4;
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
}
