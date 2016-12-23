package com.example.steph.beginerwourkoutapp;

import java.util.Date;

public class Progress {

    //constructor
    public Progress(Date date, double weight, double bmi, double calories, double bodyfate, String routineName) {
        this.date = date;
        this.weight = weight;
        this.bmi = bmi;
        this.calories = calories;
        this.bodyfat = bodyfate;
        this.routineName = routineName;
    }


    //getter and setter
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getBodyfat() {
        return bodyfat;
    }

    public void setBodyfat(double bodyfat) {
        this.bodyfat = bodyfat;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    //variables
    private Date date;
    private double weight;
    private double bmi;
    private double calories;
    private double bodyfat;
    private String routineName;
}
