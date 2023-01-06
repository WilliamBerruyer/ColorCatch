package com.example.iot_project;

import android.graphics.Color;

public class ColorModal {
    // variables for our colors,
    private String colorName;
    private final String colorHex;
    private final String colorRgb;
    private final String colorHsv;
    private final String colorCmyk;
    private final String colorTime;
    private final int liked;
    private int id;

    // constructor
    public ColorModal(int id, String colorName, String colorHex, String colorRgb, String colorHsv, String colorCmyk, int liked, String colorTime) {
        this.id = id;
        this.colorName = colorName;
        this.colorHex = colorHex;
        this.colorTime = colorTime;
        this.liked = liked;
        this.colorRgb = colorRgb;
        this.colorHsv = colorHsv;
        this.colorCmyk = colorCmyk;
    }

    // creating getter and setter methods
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorHex() {
        return colorHex;
    }

    public int getColorHexToInt(String color) {
        return Color.parseColor(color);
    }

    public String getColorTime() {
        return colorTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiked() {
        return liked;
    }

    public String getColorRgb() {
        return colorRgb;
    }

    public String getColorHsv() {
        return colorHsv;
    }

    public String getColorCmyk() {
        return colorCmyk;
    }


}
