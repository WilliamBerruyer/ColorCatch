package com.example.iot_project;

import android.graphics.Color;

public class ColorModal {
    // variables for our colors,
    // description, tracks and duration, id.
    private String colorName;
    private String colorHex;
    private String colorRgb;
    private String colorHsv;
    private String colorCmyk;
    private String colorTime;
    private int id;

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

    public int getColorHexToInt(String color){
        return Color.parseColor(color);
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(String colorRgb) {
        this.colorRgb = colorRgb;
    }

    public String getColorHsv() {
        return colorHsv;
    }

    public void setColorHsv(String colorHsv) {
        this.colorHsv = colorHsv;
    }

    public String getColorCmyk() {
        return colorCmyk;
    }

    public void setColorCmyk(String colorCmyk) {
        this.colorCmyk = colorCmyk;
    }

    public String getColorTime() {
        return colorTime;
    }

    public void setColorTime(String colorTime) {
        this.colorTime = colorTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public ColorModal(String colorName, String colorHex, String colorRgb, String colorHsv, String colorCmyk, String colorTime) {
        this.colorName = colorName;
        this.colorHex = colorHex;
        this.colorRgb = colorRgb;
        this.colorHsv = colorHsv;
        this.colorCmyk = colorCmyk;
        this.colorTime = colorTime;
    }

}
