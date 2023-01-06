package com.example.iot_project;

public class ColorItem {

    String name;
    String hex;
    String rgb;
    String hsv;
    String cmyk;
    int liked;

    public void Color() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getHsv() {
        return hsv;
    }

    public void setHsv(String hsv) {
        this.hsv = hsv;
    }

    public String getCmyk() {
        return cmyk;
    }

    public void setCmyk(String cmyk) {
        this.cmyk = cmyk;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
