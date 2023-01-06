package com.example.iot_project;

import android.graphics.Color;

public class PaletteModal {
    private final String hexOriginal;
    private final String c1;
    private final String c2;
    private final String c3;
    private final String c4;
    private final int liked;

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

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public String getC4() {
        return c4;
    }

    public int getColorHexToInt(String color) {
        return Color.parseColor(color);
    }

    public int getLiked() {
        return liked;
    }
}
