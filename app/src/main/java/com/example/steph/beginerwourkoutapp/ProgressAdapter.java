package com.example.steph.beginerwourkoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ProgressAdapter extends ArrayAdapter<Progress> {

    private Context context;
    private int resourceId;
    private ArrayList<Progress> progress;

    SharedPreferences goalsPrefs;

    public ProgressAdapter(Context context, int resourceId, ArrayList<Progress> progress) {
        super(context, resourceId, progress);

        this.context = context;
        this.resourceId = resourceId;
        this.progress = progress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resourceId, parent, false);
        }

        goalsPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        //initialize list_progress.xml elements
        TextView prgDate = (TextView) convertView.findViewById(R.id.prgDate);
        TextView prgRoutine = (TextView) convertView.findViewById(R.id.prgRoutine);
        TextView prgWeight = (TextView) convertView.findViewById(R.id.prgWeight);
        TextView prgBodyFat = (TextView) convertView.findViewById(R.id.prgBodyFat);
        TextView prgBmi = (TextView) convertView.findViewById(R.id.prgBmi);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(progress.get(position).getDate());

        prgDate.setText("Date: " + newDate);
        prgRoutine.setText("Routine: " + progress.get(position).getRoutineName());

        //compares daily weight to goal weight
        //sets color accordingly
        Double goalWeight;
        if(goalsPrefs.getString("goalWeightValue", null) == null){
            goalWeight = 0.0;
        }else {
            goalWeight = Double.parseDouble(goalsPrefs.getString("goalWeightValue", null));
        }
        Double thisWeight = progress.get(position).getWeight();
        if (thisWeight <= goalWeight)
            prgWeight.setTextColor(Color.GREEN);
        else
            prgWeight.setTextColor(Color.RED);
        prgWeight.setText("Weight: " + thisWeight);

        //compares daily body fat percentage
        //sets color accordingly
        Double goalBodyFat;
        if(goalsPrefs.getString("goalBodyFatValue", null) == null){
            goalBodyFat = 0.0;
        }else {
            goalBodyFat = Double.parseDouble(goalsPrefs.getString("goalBodyFatValue", ""));
        }
        Double thisBodyFat = progress.get(position).getBodyfat();
        if (thisBodyFat <= goalBodyFat)
            prgBodyFat.setTextColor(Color.GREEN);
        else
            prgBodyFat.setTextColor(Color.RED);
        prgBodyFat.setText("BF%: " + thisBodyFat);

        //compares bmi to goal bmi ranges
        //sets color accordingly
        Double goalMinBmi, goalMaxBmi;
        if(goalsPrefs.getString("goalMinBmiValue", null) == null){
            goalMinBmi = 0.0;
        }else {
            goalMinBmi = Double.parseDouble(goalsPrefs.getString("goalMinBmiValue", ""));
        }
        if(goalsPrefs.getString("goalMaxBmiValue", null) == null) {
            goalMaxBmi = 0.0;
        }else{
            goalMaxBmi = Double.parseDouble(goalsPrefs.getString("goalMaxBmiValue", ""));
        }
        Double thisBmi = progress.get(position).getBmi();
        if (thisBmi >= goalMinBmi && thisBmi <= goalMaxBmi)
            prgBmi.setTextColor(Color.GREEN);
        else
            prgBmi.setTextColor(Color.RED);
        prgBmi.setText("BMI: " + thisBmi);

        return convertView;
    }
}
