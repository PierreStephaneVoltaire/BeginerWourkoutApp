package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    EditText EN1, EN2, EN3, EN4, EN5, EN6;
    EditText S1, S2, S3, S4, S5, S6;
    EditText R1, R2, R3, R4, R5, R6;
    EditText exerciseName;
    Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        Intent intent = getIntent();
        routine = intent.getParcelableExtra("RoutineObj");
        editTextReferences();
        displayView();
        EditText[] editTexts = {EN2, EN3, EN4, EN5, EN6, S2, S3, S4, S5, S6, R2, R3, R4, R5, R6};
        final EditText[][] nonEmptyEditTexts = {{EN1, R1, S1}, {EN2, R2, S2}, {EN3, R3, S3}, {EN4, R4, S4}, {EN5, S5, R5}, {EN6, R6, S6}};

        if (routine != null) {
            String routineName = routine.getRoutineName().toString();
            exerciseName.setText(routineName);
            exerciseName.setEnabled(false);
            if (routine.getListOfExercises() != null) {

                int size = routine.getListOfExercises().size();


                ArrayList<Exercises> exercises = routine.getListOfExercises();
                for (int i = 0; i < size; i++) {
                    nonEmptyEditTexts[i][0].setText(exercises.get(i).getExercisename().toString());
                    nonEmptyEditTexts[i][1].setText(String.valueOf(exercises.get(i).getRep()));
                    nonEmptyEditTexts[i][2].setText(String.valueOf(exercises.get(i).getSet()));

                }

            }

        }
        for (int i = 0; i < 15; i++) {
            if (editTexts[i].getText().toString().trim().length() == 0)
                editTexts[i].setVisibility(View.GONE);
        }
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            nonEmptyEditTexts[i][0].addTextChangedListener(
                    new TextWatcher() {

                        public void afterTextChanged(Editable s) {
                        }

                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (nonEmptyEditTexts[finalI][0].getText().toString().trim().length() == 0 &&
                                    nonEmptyEditTexts[finalI][1].getText().toString().trim().length() == 0 &&
                                    nonEmptyEditTexts[finalI][2].getText().toString().trim().length() == 0) {
                                for (int y = 0; y < 3; y++) {
                                    nonEmptyEditTexts[finalI + 1][y].setVisibility(View.GONE);
                                }
                            } else {
                                for (int y = 0; y < 3; y++) {
                                    nonEmptyEditTexts[finalI + 1][y].setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }

            );
        }

    }

    private void editTextReferences(){
        //Get reference on Edit Text
        exerciseName = (EditText) findViewById(R.id.workoutName);
        EN1 = (EditText) findViewById(R.id.en1);
        EN2 = (EditText) findViewById(R.id.en2);
        EN3 = (EditText) findViewById(R.id.en3);
        EN4 = (EditText) findViewById(R.id.en4);
        EN5 = (EditText) findViewById(R.id.en5);
        EN6 = (EditText) findViewById(R.id.en6);
        S1 = (EditText) findViewById(R.id.s1);
        S2 = (EditText) findViewById(R.id.s2);
        S3 = (EditText) findViewById(R.id.s3);
        S4 = (EditText) findViewById(R.id.s4);
        S5 = (EditText) findViewById(R.id.s5);
        S6 = (EditText) findViewById(R.id.s6);
        R1 = (EditText) findViewById(R.id.r1);
        R2 = (EditText) findViewById(R.id.r2);
        R3 = (EditText) findViewById(R.id.r3);
        R4 = (EditText) findViewById(R.id.r4);
        R5 = (EditText) findViewById(R.id.r5);
        R6 = (EditText) findViewById(R.id.r6);
    }

    private void displayView(){

    }

    public void saveRoutines(View view) {
        EditText[][] editTexts = {{EN1, S1, R1}, {EN2, S2, R2}, {EN3, S3, R3}, {EN4, S4, R4}, {EN5, S5, R5}, {EN6, S6, R6}};

        ArrayList<Exercises> exercises2 = new ArrayList<>();
        if (editTexts[0][0].getText().toString().trim().length() == 0
                || editTexts[0][1].getText().toString().trim().length() == 0
                || editTexts[0][2].getText().toString().trim().length() == 0) {
        } else {
            for (int i = 0; i < 5; i++) {
                if (editTexts[i][0].getText().toString().trim().length() != 0 && editTexts[i][1].getText().toString().trim().length() != 0 && editTexts[i][2].getText().toString().trim().length() != 0) {
                    String Exercisename = editTexts[i][0].getText().toString();
                    int set = Integer.parseInt(editTexts[i][1].getText().toString());
                    int rep = Integer.parseInt(editTexts[i][2].getText().toString());
                    Exercises exercises = new Exercises();
                    exercises.setExercisename(Exercisename);
                    exercises.setSet(set);
                    exercises.setRep(rep);
                    exercises2.add(exercises);
                }
            }
            Routine routine = new Routine();
            routine.setListOfExercises(exercises2);
            routine.setRoutineName(exerciseName.getText().toString());
            dbhandler db = new dbhandler(this);
            db.updateRoutine(routine);
            finish();
        }
    }

    public void close(View view) {
        finish();
    }
}
