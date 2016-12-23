package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        MakeProgressTable();


    }

    public void MakeProgressTable() {
        dbhandler db = new dbhandler(this);
        try {
            //gets all rows from Progress table
            ArrayList<Progress> progressList = db.getProgress();

            //sets list_progress to an adapter
            ProgressAdapter adapter = new ProgressAdapter(this, R.layout.list_progress, progressList);

            //initializes lstProgress
            ListView lstProgress = (ListView) findViewById(R.id.lstProgress);

            //list_progress layout within
            lstProgress.setAdapter(adapter);
        } catch (ParseException e) {
            e.printStackTrace();
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

        //sets menu button actions
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
            Toast.makeText(ProgressActivity.this, "You are in this activity", Toast.LENGTH_SHORT).show();
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