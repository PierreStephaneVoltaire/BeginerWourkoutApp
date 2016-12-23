package com.example.steph.beginerwourkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaloriesActivity extends AppCompatActivity {
    TextView total;
    EditText calories;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        //get references
        add = (Button) findViewById(R.id.Add);
        calories = (EditText) findViewById(R.id.Calories);
        total = (TextView) findViewById(R.id.Total);
        setNumberCalTextView();
    }

    //get total calories on current date and set Text to show total calories.
    public void setNumberCalTextView() {
        dbhandler db = new dbhandler(this);
        int daylyCalories = db.selectNutrition();
        total.setText("Total: " + daylyCalories);

    }

    //when start this activity inflate menu bar
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
            Toast.makeText(CaloriesActivity.this, "You are in this activity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_swatch) {
            Intent intent = new Intent(getApplicationContext(), StopwatchActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //add the calories to nutrition table in database
    public void addcall(View view) {
        dbhandler db = new dbhandler(this);

        // add it only if it is not empty
        if (isEmpty(calories) == false) {
            int cal = Integer.parseInt(calories.getText().toString());
            db.addNutrition(cal);
            int daylyCalories = db.selectNutrition();
            total.setText("Total: " + daylyCalories);

        } else {
            total.setText("You did not enter a valid number");
        }
    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
