package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GoalsActivity extends AppCompatActivity {

    EditText goalWeight;
    ToggleButton goalUnit;
    EditText goalBodyFat;
    EditText goalMinBmi;
    EditText goalMaxBmi;

    SharedPreferences goalsPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        goalWeight = (EditText) findViewById(R.id.goalWeight);
        goalUnit = (ToggleButton) findViewById(R.id.goalUnit);
        goalBodyFat = (EditText) findViewById(R.id.goalBodyFat);
        goalMinBmi = (EditText) findViewById(R.id.goalMinBmi);
        goalMaxBmi = (EditText) findViewById(R.id.goalMaxBmi);

        goalsPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void saveGoals(View view) {
        String goalWeightValue = goalWeight.getText().toString();
        Boolean goalUnitValue = goalUnit.isChecked();
        if (goalUnitValue == false)
            goalWeightValue = Double.toString(Double.parseDouble(goalWeightValue) * 2.2);
        String goalBodyFatValue = goalBodyFat.getText().toString();
        String goalMinBmiValue = goalMinBmi.getText().toString();
        String goalMaxBmiValue = goalMaxBmi.getText().toString();

        if (goalWeightValue.length() < 1
                || goalBodyFatValue.length() < 1
                || goalMinBmiValue.length() < 1
                || goalMaxBmiValue.length() < 1) {
            Toast.makeText(this, "Must fill all fields to save", Toast.LENGTH_LONG).show();
        } else {
            if (Integer.parseInt(goalBodyFatValue) < 1 || Integer.parseInt(goalBodyFatValue) > 75) {
                Toast.makeText(this, "Range for Body Fat Percentage should be 1-75%", Toast.LENGTH_LONG).show();
            } else {
                if (Integer.parseInt(goalMinBmiValue) > Integer.parseInt(goalMaxBmiValue)) {
                    Toast.makeText(this, "Minimum BMI should be less than maximum BMI", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = goalsPrefs.edit();

                    editor.putString("goalWeightValue", goalWeightValue)
                            .putString("goalBodyFatValue", goalBodyFatValue)
                            .putBoolean("goalUnitValue", goalUnitValue)
                            .putString("goalMinBmiValue", goalMinBmiValue)
                            .putString("goalMaxBmiValue", goalMaxBmiValue)
                            .apply();

                    Toast.makeText(this, "Goals saved.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void clearGoals(View view) {
        goalWeight.setText("");
        goalBodyFat.setText("");
        goalUnit.setChecked(false);
        goalMinBmi.setText("");
        goalMaxBmi.setText("");

        SharedPreferences.Editor editor = goalsPrefs.edit();

        editor.remove("goalWeightValue")
                .remove("goalBodyFatValue")
                .remove("goalUnitValue")
                .remove("goalMinBmiValue")
                .remove("goalMaxBmiValue").commit();
        Toast.makeText(this, "Goals cleared.", Toast.LENGTH_SHORT).show();
    }

    public void cancelGoals(View view) {
        finish();
        Toast.makeText(this, "No changes applied for goals.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String defGoalWeight = goalsPrefs.getString("goalWeightValue", "");
        String defGoalBodyFat = goalsPrefs.getString("goalBodyFatValue", "");
        Boolean defGoalUnit = goalsPrefs.getBoolean("goalUnitValue", true);
        String defGoalMinBmi = goalsPrefs.getString("goalMinBmiValue", "");
        String defGoalMaxBmi = goalsPrefs.getString("goalMaxBmiValue", "");

        goalWeight.setText(defGoalWeight);
        goalBodyFat.setText(defGoalBodyFat);
        goalUnit.setChecked(defGoalUnit);
        goalMinBmi.setText(defGoalMinBmi);
        goalMaxBmi.setText(defGoalMaxBmi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_always) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_profile) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_goals) {
            Toast.makeText(GoalsActivity.this, "You are in this acitivity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_routine) {
            Intent intent = new Intent(getApplicationContext(), RoutineActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_progress) {
            Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_calories) {
            Intent intent = new Intent(getApplicationContext(), CaloriesActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_swatch) {
            Intent intent = new Intent(getApplicationContext(), StopwatchActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
