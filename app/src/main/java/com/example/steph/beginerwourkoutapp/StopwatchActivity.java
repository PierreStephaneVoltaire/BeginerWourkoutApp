package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class StopwatchActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Chronometer chronometer;
    Button btnStartOrStop;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        //get reference on chronometer and button
        chronometer = (Chronometer) findViewById(R.id.stopwatch);
        btnStartOrStop = (Button) findViewById(R.id.btnStartOrStop);
        count = 0;
    }

    public void startOrStop(View view) {
        // base on button text call start or stop timer
        if (btnStartOrStop.getText().equals("Start")) {
            startTimer(count);
            count++;
        } else {
            stopTimer();
        }
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
            Toast.makeText(StopwatchActivity.this, "You are in this activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void startTimer(int count) {
        //start stopwatch and change button text to stop
        btnStartOrStop.setText("Stop");
        if(count == 0) {
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
        chronometer.start();

    }

    private void stopTimer() {
        //change button to start and stop stopwatch but keep display time
        // if they press this button again then it will resume from where it stopped.
        btnStartOrStop.setText("Start");
        chronometer.stop();
    }

    public void reset(View view) {
        //When user pressed reset then set chronometer base to null again and set text 00:00
        //change button to start ans stop stopwatch.
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setText("00:00");
        btnStartOrStop.setText("Start");
        chronometer.stop();
    }
}


