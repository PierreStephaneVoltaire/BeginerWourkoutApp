package com.example.steph.beginerwourkoutapp;

import android.os.Parcel;
import android.os.Parcelable;


public class Exercises implements Parcelable {
    //default constructor
    public Exercises() {
    }

    protected Exercises(Parcel in) {
        exercisename = in.readString();
        set = in.readInt();
        rep = in.readInt();
    }

    public static final Creator<Exercises> CREATOR = new Creator<Exercises>() {
        @Override
        public Exercises createFromParcel(Parcel in) {
            return new Exercises(in);
        }

        @Override
        public Exercises[] newArray(int size) {
            return new Exercises[size];
        }
    };

    //getters and setters
    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getExercisename() {
        return exercisename;
    }

    public void setExercisename(String exercisename) {
        this.exercisename = exercisename;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    //variables
    private String exercisename;
    private int set;
    private int rep;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exercisename);
        dest.writeInt(set);
        dest.writeInt(rep);
    }
}
