package com.example.iot_project;

import android.graphics.Color;

public class ColorModal {
    // variables for our colors,
    private String colorName;
    private String colorHex;
    private String colorTime;
    private int liked;
    private int id;

    // constructor
    public ColorModal(String colorName, String colorHex, int liked, String colorTime) {
        this.colorName = colorName;
        this.colorHex = colorHex;
        this.colorTime = colorTime;
        this.liked = liked;
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
}
