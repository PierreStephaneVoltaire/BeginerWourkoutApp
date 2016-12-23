package com.example.steph.beginerwourkoutapp;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

//Routine class hold Routine Name and List of Exercise in Routine.
// this implement parcelable.
public class Routine implements Parcelable {


    protected Routine(Parcel in) {
        RoutineName = in.readString();
        listOfExercises = in.createTypedArrayList(Exercises.CREATOR);
    }

    public static final Creator<Routine> CREATOR = new Creator<Routine>() {
        @Override
        public Routine createFromParcel(Parcel in) {
            return new Routine(in);
        }

        @Override
        public Routine[] newArray(int size) {
            return new Routine[size];
        }
    };

    //getter
    public String getRoutineName() {
        return RoutineName;
    }

    public void setRoutineName(String routineName) {
        RoutineName = routineName;
    }

    //setter
    public ArrayList<Exercises> getListOfExercises() {
        return listOfExercises;
    }

    public void setListOfExercises(ArrayList<Exercises> listOfExercises) {
        this.listOfExercises = listOfExercises;
    }

    public Routine(String routineName, ArrayList<Exercises> listOfExercises) {
        RoutineName = routineName;
        this.listOfExercises = listOfExercises;
    }

    //constructors
    public Routine() {
    }

    public Routine(String routineName) {
        RoutineName = routineName;
    }

    // declare variables
    private String RoutineName;
    private ArrayList<Exercises> listOfExercises;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(RoutineName);
        dest.writeTypedList(listOfExercises);
    }
}
