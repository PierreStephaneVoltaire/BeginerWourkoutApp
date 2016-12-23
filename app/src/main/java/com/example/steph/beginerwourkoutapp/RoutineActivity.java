package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class RoutineActivity extends AppCompatActivity implements LstFragment.OnworkoutlineSelectedListener {
    Button btn;
    LstFragment lstfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        LoadListFragment();
    }

    public void LoadListFragment() {

        lstfragment = (LstFragment) getSupportFragmentManager().findFragmentByTag("lstfragment");
        if (lstfragment == null) {
            lstfragment = new LstFragment();
            FragmentTransaction transact = getSupportFragmentManager().beginTransaction();
            transact.add(android.R.id.content, lstfragment, "lstfragment");
            transact.commit();

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
            Toast.makeText(RoutineActivity.this, "You are in this activity", Toast.LENGTH_SHORT).show();
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

    final static int result = 42;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == result) {
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void onRoutineSelected(int position, String routineName) {
        Bundle bundle = new Bundle();
        bundle.putString("routine", routineName);
        AddRoutineFragment fragment = new AddRoutineFragment();
        fragment.setArguments(bundle);
        FragmentManager transact = getSupportFragmentManager();
        transact.beginTransaction()
                .replace(android.R.id.content, fragment, "routinedetails")
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void addOrReplaceRoutine() {
        Intent
                intent = new Intent(getApplicationContext(), AddRoutineActivity.class);
        startActivityForResult(intent, result);
    }

}
