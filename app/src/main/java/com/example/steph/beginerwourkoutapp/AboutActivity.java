package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {
    //this is about activity that show who build this app and what is this app for.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    //menu bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_always) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_profile) {
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
        return super.onOptionsItemSelected(item);
    }
}
