package com.example.valentinvaleanu.pushyourself;

/**
 * Created by Tina on 07-Nov-17.
 */

public class Exercises {
    private int imgSrc;
    private String name;

    public Exercises(int imgSrc, String name) {
        this.imgSrc = imgSrc;
        this.name = name;
    }

    public void setImgSrc(int imgSrc) {

        this.imgSrc = imgSrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgSrc() {

        return imgSrc;
    }

    public String getName() {
        return name;
    }
}
