package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    Intent intent;
    SharedPreferences profilePrefs;
    Button btnGoals, btnRoutine, btnProgress, btnCalories, btnStopwatch;
    private double weight, height, bodyfat, bmi = 0;
    private int age = 0, sex = -1;
    private boolean filledProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this); //to check SQLite Database and Shared Preference in Chrome
        //get reference on main
        btnGoals = (Button) findViewById(R.id.btnGoals);
        btnRoutine = (Button) findViewById(R.id.btnRoutine);
        btnProgress = (Button) findViewById(R.id.btnProgress);
        btnCalories = (Button) findViewById(R.id.btnCalroies);
        btnStopwatch = (Button) findViewById(R.id.btnStopwatch);
        //Call checkProfile to check if user created profile or not
        checkProfile();
    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //Set intent on each menu that it will send to activity that user clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(filledProfile) {
            int id = item.getItemId();
            if (id == R.id.action_always) {
                Toast.makeText(MainActivity.this, "You are in this activity", Toast.LENGTH_SHORT).show();
            }else if (id == R.id.action_profile) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            } else if (id == R.id.action_goals) {
                Intent intent = new Intent(getApplicationContext(), GoalsActivity.class);
                startActivity(intent);
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
        }
            return super.onOptionsItemSelected(item);
    }

    //Send to Calories Activity
    public void goCalories(View view) {
        Intent intent = new Intent(this, CaloriesActivity.class);
        startActivity(intent);

    }

    //send to Goal Activity
    public void goGoals(View view) {
        intent = new Intent(getApplicationContext(), GoalsActivity.class);
        startActivity(intent);
    }

    //send user profile preference activity
    public void goProfile(View view) {

        intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }

    //send user to stopwatch activity
    public void goStopwatch(View view) {
        intent = new Intent(getApplicationContext(), StopwatchActivity.class);
        startActivity(intent);
    }

    //send user to routine activity
    public void goRoutine(View view) {
        intent = new Intent(getApplicationContext(), RoutineActivity.class);
        startActivity(intent);
    }

    //send user to progress activity
    public void goProgress(View view) {
        intent = new Intent(getApplicationContext(), ProgressActivity.class);
        startActivity(intent);
    }

    private void checkProfile() {
        profilePrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //if user runs first time, no shared preference exist it will disable all button except profile button and toast to create profile
        if (profilePrefs.getAll().isEmpty()) {
            setButton(false);
            filledProfile = false;
            Toast.makeText(this, "No Profile Detected.\nPlease Create a Profile.", Toast.LENGTH_SHORT).show();
            // if one of profile is not saved then toast message that profile is incomplete and must complete a profile
        } else if (profilePrefs.getString("fName", null) == null || profilePrefs.getString("lName", null) == null ||
                profilePrefs.getString("sex", null) == null || profilePrefs.getString("age", null) == null ||
                profilePrefs.getString("height", null) == null || profilePrefs.getString("weight", null) == null) {
            setButton(false);
            Toast.makeText(this, "Incomplete Profile!\nPlease complete a profile", Toast.LENGTH_SHORT).show();
        } else {
            //if profile preference completed then enable all buttons
            setButton(true);
            filledProfile= true;
        }
    }


    //set button enabled or disabled.
    private void setButton(Boolean b) {
        btnGoals.setEnabled(b);
        btnProgress.setEnabled(b);
        btnRoutine.setEnabled(b);
        btnCalories.setEnabled(b);
        btnStopwatch.setEnabled(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkProfile();
        //get shared preference file
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //create a listener
        prefs.registerOnSharedPreferenceChangeListener(this);


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String s) {

        SharedPreferences.Editor prefsEdit = prefs.edit();

        //switch statement for validation on profile shared preference
        switch (s) {
            case "height":
                if (prefs.getString("height", null) != null) {
                    //get height
                    height = Double.parseDouble(prefs.getString("height", "0"));
                    //if height is taller than 250 cm then toast error message and remove it from shared preference
                    if (height > 250) {
                        Toast.makeText(this, "Invalid Input!\nPlease Insert valid input. (Under 250 cm)", Toast.LENGTH_SHORT).show();
                        prefsEdit.remove(s);
                        prefsEdit.commit();
                    }
                }
                break;
            case "weight":
                if (prefs.getString("weight", null) != null) {
                    //get weight
                    weight = Double.parseDouble(prefs.getString("weight", "0"));
                    //Toast Message and remove weight from shared preference if weight is more than 630 kg
                    if (weight > 630) {
                        Toast.makeText(this, "Invalid Input!\nPlease Insert valid input. (1400 LB)", Toast.LENGTH_SHORT).show();
                        prefsEdit.remove(s);
                        prefsEdit.commit();
                    }
                }
                break;
            case "age":
                if (prefs.getString("age", null) != null) {
                    age = Integer.parseInt(prefs.getString("age", "0"));
                    if (age > 120) {
                        //check age and if it is over 120 toast error message and remove from shared preference
                        Toast.makeText(this, "Invalid Input!\nPlease Insert valid input. (Under age 120)", Toast.LENGTH_SHORT).show();
                        prefsEdit.remove(s);
                        prefsEdit.commit();
                    }
                }
                break;
            case "sex":
                if (prefs.getString("sex", null) != null) {
                    //get value sex in 1(male) or 0(female)
                    sex = Integer.parseInt(prefs.getString("sex", null));
                }
                break;
        }

        dbhandler db = new dbhandler(getApplicationContext());
        double calories = db.selectNutrition();
        Date date = new Date();

        bmi = (weight / 2.2) / Math.pow(height / 100, 2);
        bmi = Math.round(100 * bmi) / 100;
        bodyfat = ((1.20 * bmi) + (0.23 * age) - (10.8 * sex) - 5.4);
        bodyfat = Math.round(100 * bodyfat) / 100;
        //creates a new row for Progress everytime a change in the profile occurs
        Progress changedProgress = new Progress(date, weight, bmi, calories, bodyfat, null);
        db.addProgress(changedProgress);
    }

}


