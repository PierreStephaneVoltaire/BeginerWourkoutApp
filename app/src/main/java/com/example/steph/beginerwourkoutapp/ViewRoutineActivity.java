package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewRoutineActivity extends AppCompatActivity {

    TextView viewTxtRoutineName;
    TextView viewTxtEx1Name, viewTxtEx1Set, viewTxtEx1Rep;
    TextView viewTxtEx2Name, viewTxtEx2Set, viewTxtEx2Rep;
    TextView viewTxtEx3Name, viewTxtEx3Set, viewTxtEx3Rep;
    TextView viewTxtEx4Name, viewTxtEx4Set, viewTxtEx4Rep;
    TextView viewTxtEx5Name, viewTxtEx5Set, viewTxtEx5Rep;
    TextView viewTxtEx6Name, viewTxtEx6Set, viewTxtEx6Rep;
    Spinner spnViewEx1Set, spnViewEx1Rep, spnViewEx2Set, spnViewEx2Rep, spnViewEx3Set, spnViewEx3Rep;
    Spinner spnViewEx4Set, spnViewEx4Rep, spnViewEx5Set, spnViewEx5Rep, spnViewEx6Set, spnViewEx6Rep;
    boolean ex1Set, ex1Rep, ex2Set, ex2Rep, ex3Set, ex3Rep, ex4Set, ex4Rep, ex5Set, ex5Rep, ex6Set, ex6Rep;
    ArrayList<Exercises> exercises;

    Intent intent;
    Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routine);
        populateView(); //call this method to display view
    }


    public void populateView() {
        intent = getIntent();
        //get routine object
        routine = intent.getParcelableExtra("RoutineObj");
        getTextViewReference();
        getSpinnerReference();
        //set text routine name
        viewTxtRoutineName.setText(routine.getRoutineName());

        //setting textViewArray
        TextView[][] txtViewArray = {{viewTxtEx1Name, viewTxtEx1Set, viewTxtEx1Rep},
                {viewTxtEx2Name, viewTxtEx2Set, viewTxtEx2Rep},
                {viewTxtEx3Name, viewTxtEx3Set, viewTxtEx3Rep},
                {viewTxtEx4Name, viewTxtEx4Set, viewTxtEx4Rep},
                {viewTxtEx5Name, viewTxtEx5Set, viewTxtEx5Rep},
                {viewTxtEx6Name, viewTxtEx6Set, viewTxtEx6Rep}};

        //setting spinnerArray
        Spinner[][] spinnerArray = {{spnViewEx1Set, spnViewEx1Rep}, {spnViewEx2Set, spnViewEx2Rep},
                {spnViewEx3Set, spnViewEx3Rep}, {spnViewEx4Set, spnViewEx4Rep},
                {spnViewEx5Set, spnViewEx5Rep}, {spnViewEx6Set, spnViewEx6Rep}};

        //if routine is not null then get list of exercise and setting in TextView
        if (routine != null) {
            exercises = routine.getListOfExercises();
            //loop through textViewArray to set each exercise name, set, and rep
            // for spinner call addItemsOnSpinner Method to dynamically add value according
            // to value of set and rep.
            for (int i = 0; i < exercises.size(); i++) {
                txtViewArray[i][0].setText(exercises.get(i).getExercisename().toString());
                txtViewArray[i][1].setText(String.valueOf(exercises.get(i).getSet()));
                addItemsOnSpinner(exercises.get(i).getSet(), spinnerArray[i][0]);
                spinnerArray[i][0].setOnItemSelectedListener(myListener);
                spinnerArray[i][0].setVisibility(View.VISIBLE);
                txtViewArray[i][2].setText(String.valueOf(exercises.get(i).getRep()));
                addItemsOnSpinner(exercises.get(i).getRep(), spinnerArray[i][1]);
                spinnerArray[i][1].setOnItemSelectedListener(myListener);
                spinnerArray[i][1].setVisibility(View.VISIBLE);
            }
        }
    }

    //spinner listener get value that are selected from each value and if it match with
    //correspondent set or rep value then set true
    OnItemSelectedListener myListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.spnViewEx1Set:
                    if (exercises.get(0).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex1Set = true;
                    } else {
                        ex1Set = false;
                    }
                    break;
                case R.id.spnViewEx1Rep:
                    if (exercises.get(0).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex1Rep = true;
                    } else {
                        ex1Rep = false;
                    }
                    break;
                case R.id.spnViewEx2Set:
                    if (exercises.get(1).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex2Set = true;
                    } else {
                        ex2Set = false;
                    }
                    break;
                case R.id.spnViewEx2Rep:
                    if (exercises.get(1).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex2Rep = true;
                    } else {
                        ex2Rep = false;
                    }
                    break;
                case R.id.spnViewEx3Set:
                    if (exercises.get(2).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex3Set = true;
                    } else {
                        ex3Set = false;
                    }
                    break;
                case R.id.spnViewEx3Rep:
                    if (exercises.get(2).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex3Rep = true;
                    } else {
                        ex3Rep = false;
                    }
                    break;
                case R.id.spnViewEx4Set:
                    if (exercises.get(3).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex4Set = true;
                    } else {
                        ex4Set = false;
                    }
                    break;
                case R.id.spnViewEx4Rep:
                    if (exercises.get(3).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex4Rep = true;
                    } else {
                        ex4Rep = false;
                    }
                    break;
                case R.id.spnViewEx5Set:
                    if (exercises.get(4).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex5Set = true;
                    } else {
                        ex5Set = false;
                    }
                    break;
                case R.id.spnViewEx5Rep:
                    if (exercises.get(4).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex5Rep = true;
                    } else {
                        ex5Rep = false;
                    }
                    break;
                case R.id.spnViewEx6Set:
                    if (exercises.get(5).getSet() == (Integer) parent.getItemAtPosition(position)) {
                        ex6Set = true;
                    } else {
                        ex6Set = false;
                    }
                    break;
                case R.id.spnViewEx6Rep:
                    if (exercises.get(5).getRep() == (Integer) parent.getItemAtPosition(position)) {
                        ex6Rep = true;
                    } else {
                        ex6Rep = false;
                    }
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    //get TextView References
    private void getTextViewReference() {
        viewTxtRoutineName = (TextView) findViewById(R.id.txtViewRoutineName);
        viewTxtEx1Name = (TextView) findViewById(R.id.txtViewEx1Name);
        viewTxtEx1Set = (TextView) findViewById(R.id.txtViewEx1Set);
        viewTxtEx1Rep = (TextView) findViewById(R.id.txtViewEx1Rep);
        viewTxtEx2Name = (TextView) findViewById(R.id.txtViewEx2Name);
        viewTxtEx2Set = (TextView) findViewById(R.id.txtViewEx2Set);
        viewTxtEx2Rep = (TextView) findViewById(R.id.txtViewEx2Rep);
        viewTxtEx3Name = (TextView) findViewById(R.id.txtViewEx3Name);
        viewTxtEx3Set = (TextView) findViewById(R.id.txtViewEx3Set);
        viewTxtEx3Rep = (TextView) findViewById(R.id.txtViewEx3Rep);
        viewTxtEx4Name = (TextView) findViewById(R.id.txtViewEx4Name);
        viewTxtEx4Set = (TextView) findViewById(R.id.txtViewEx4Set);
        viewTxtEx4Rep = (TextView) findViewById(R.id.txtViewEx4Rep);
        viewTxtEx5Name = (TextView) findViewById(R.id.txtViewEx5Name);
        viewTxtEx5Set = (TextView) findViewById(R.id.txtViewEx5Set);
        viewTxtEx5Rep = (TextView) findViewById(R.id.txtViewEx5Rep);
        viewTxtEx6Name = (TextView) findViewById(R.id.txtViewEx6Name);
        viewTxtEx6Set = (TextView) findViewById(R.id.txtViewEx6Set);
        viewTxtEx6Rep = (TextView) findViewById(R.id.txtViewEx6Rep);
    }

    //get Spinner References
    private void getSpinnerReference() {
        spnViewEx1Set = (Spinner) findViewById(R.id.spnViewEx1Set);
        spnViewEx1Rep = (Spinner) findViewById(R.id.spnViewEx1Rep);
        spnViewEx2Set = (Spinner) findViewById(R.id.spnViewEx2Set);
        spnViewEx2Rep = (Spinner) findViewById(R.id.spnViewEx2Rep);
        spnViewEx3Set = (Spinner) findViewById(R.id.spnViewEx3Set);
        spnViewEx3Rep = (Spinner) findViewById(R.id.spnViewEx3Rep);
        spnViewEx4Set = (Spinner) findViewById(R.id.spnViewEx4Set);
        spnViewEx4Rep = (Spinner) findViewById(R.id.spnViewEx4Rep);
        spnViewEx5Set = (Spinner) findViewById(R.id.spnViewEx5Set);
        spnViewEx5Rep = (Spinner) findViewById(R.id.spnViewEx5Rep);
        spnViewEx6Set = (Spinner) findViewById(R.id.spnViewEx6Set);
        spnViewEx6Rep = (Spinner) findViewById(R.id.spnViewEx6Rep);
    }

    //add items into spinner dynamically
    private void addItemsOnSpinner(int setOrRep, Spinner spinner) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= setOrRep; i++) {
            list.add(i);
        }

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void viewDone(View view) { //when done button is click check for if user finish routine then update database
        boolean allRecordMatch = false;
        boolean[][] booleanArray = {{ex1Set, ex1Rep}, {ex2Set, ex2Rep}, {ex3Set, ex3Rep},
                {ex4Set, ex4Rep}, {ex5Set, ex5Rep}, {ex6Set, ex6Rep}};
        for (int i = 0; i < exercises.size(); i++) { //check through boolean array see if every boolean array is true
            if (booleanArray[i][0] && booleanArray[i][1]) {
                allRecordMatch = true;
            } else { //set false if one of boolean array is false
                allRecordMatch = false;
            }
        }

        if(allRecordMatch){ //if allRecordMatch is true then update progress table
            dbhandler dbhandler = new dbhandler(this);

            long updated = dbhandler.updateProgress(routine.getRoutineName());
            if(updated > 0) //if return value is more than 0 toast success message if not toast failed message
                Toast.makeText(this, "Progress Table Successfully Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Progress Table Update Failed", Toast.LENGTH_SHORT).show();
        }else{
            //if allRecordMatch is false then toast exercise is not done
            Toast.makeText(this, "Exercise Incomplete", Toast.LENGTH_SHORT).show();
        }
        //finish activity
        finish();
    }

    public void close(View view) {
        finish();
    }
}
