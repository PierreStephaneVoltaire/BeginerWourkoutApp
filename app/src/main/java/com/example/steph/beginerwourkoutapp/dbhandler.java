package com.example.steph.beginerwourkoutapp; /**
 * Created by steph on 11/3/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class dbhandler {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    private static final int DATABASE_VERSION = 1;
    //set table names
    private static final String DATABASE_NAME = "Gym.db";
    private static final String TABLE_ROUTINE = "routine";
    private static final String TABLE_PROGRESS = "progress";
    private static final String TABLE_NUTRISION = "nutrision";
    private static final String TABLE_EXERCISE = "exercise";
    private static final String TABLE_WORKOUT = "workout";
    // Shops Table Columns names
    private static final String KEY_ROUTINE = "routine_name";
    private static final String KEY_WORKOUTID = "workout_id";
    private static final String KEY_WORKOUTID_W = "_id";
    //exercise
    private static final String KEY_EXERCISE = "exercise_name";
    //workout
    private static final String KEY_EXERCISEID = "exercise_id";
    private static final String KEY_SET = "sets";
    private static final String KEY_REP = "rep";
    //progress
    private static final String KEY_PROG_DATE = "date";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_BMI = "bmi";
    private static final String KEY_PROG_CALORIES = "calories";
    private static final String KEY_BODYFAT = "bodyfat";
    private static final String KEY_PROG_ROUTINE = "routine_name";
    //nutrition column
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_NU_DATE = "date";


    //ddl statements
    private static final String CREATE_ROUTINE_TABLE = " CREATE TABLE " + TABLE_ROUTINE + " ( " + KEY_ROUTINE + " TEXT UNIQUE, " + KEY_WORKOUTID + " INTEGER PRIMARY KEY)";

    private static final String CREATE_EXERCISE_TABLE = " CREATE TABLE " + TABLE_EXERCISE + " ( " + KEY_EXERCISE + " TEXT NOT NULL UNIQUE, " + KEY_EXERCISEID + " INTEGER PRIMARY KEY )";

    private static final String CREATE_WORKOUT_TABLE = " CREATE TABLE " + TABLE_WORKOUT + " ( " + KEY_EXERCISE + " TEXT NOT NULL , " + KEY_ROUTINE + " TEXT NOT NULL , " + KEY_SET + " INTEGER, " + KEY_REP + " INTEGER )";

    private static final String CREATE_PROGRESS_TABLE = " CREATE TABLE " + TABLE_PROGRESS + " ( " + KEY_PROG_DATE + " DATE PRIMARY KEY, " +
            KEY_WEIGHT + " FLOAT NOT NULL, " + KEY_BMI + " FLOAT, " + KEY_CALORIES + " INTEGER, " + KEY_BODYFAT + " INTEGER, " + KEY_PROG_ROUTINE + " TEXT) ";

    private static final String CREATE_NUTRITION_TABLE = " CREATE TABLE " + TABLE_NUTRISION +
            " ( " + KEY_NU_DATE + " DATE PRIMARY KEY, " + KEY_CALORIES + " INTEGER NOT NULL )";

    //instance of openhelper
    public dbhandler(Context context) {

        openHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


//delete routine

    public void deleteRoutine(String routineName) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.delete(TABLE_ROUTINE, KEY_ROUTINE + " = ?",
                new String[]{String.valueOf(routineName)});
        db.delete(TABLE_WORKOUT, KEY_ROUTINE + " = ?",
                new String[]{String.valueOf(routineName)});
        db.close();

    }

    //selecting all routine
    public ArrayList<String> selectAllRoutines() {
        ArrayList<String> routineArrayList = new ArrayList<String>();

        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_ROUTINE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String RoutineName = cursor.getString(cursor.getColumnIndex(KEY_ROUTINE));
            routineArrayList.add(RoutineName);

        }

        sqLiteDatabase.close();
        return routineArrayList;

    }

    //select only one routine
    public Routine selectRoutine(String routineName) {
        Routine routine = new Routine();

        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();
        String selection = KEY_ROUTINE + " = ?";
        Cursor cursor = sqLiteDatabase.query(TABLE_ROUTINE, null, selection, new String[]{routineName}, null, null, null);

        if (cursor.moveToNext()) {
            String RoutineName = cursor.getString(cursor.getColumnIndex(KEY_ROUTINE));
            selection = KEY_ROUTINE + " = ?";
            Cursor cursor2 = sqLiteDatabase.query(TABLE_WORKOUT, null, selection, new String[]{RoutineName}, null, null, null);
            ArrayList<Exercises> arrayList = new ArrayList<Exercises>();
            //get list of exercise in one routine
            while (cursor2.moveToNext()) {
                Exercises exercises = new Exercises();
                exercises.setExercisename(cursor2.getString(cursor2.getColumnIndex(KEY_EXERCISE)));
                exercises.setRep(cursor2.getInt(cursor2.getColumnIndex(KEY_REP)));
                exercises.setSet(cursor2.getInt(cursor2.getColumnIndex(KEY_SET)));
                arrayList.add(exercises);
            }
            routine.setRoutineName(RoutineName);
            routine.setListOfExercises(arrayList);
        }
        sqLiteDatabase.close();
        return routine;
    }

    //update routine
    public void updateRoutine(Routine routine) {
        Routine routine1 = selectRoutine(routine.getRoutineName());
        if (routine1.getRoutineName() != null) {
            deleteRoutine(routine1.getRoutineName());
        }
        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_ROUTINE, routine.getRoutineName().toString());
        Log.wtf("routine name", routine.getRoutineName().toString());
        ArrayList<Exercises> exercises = routine.getListOfExercises();
        ContentValues cvExercise = new ContentValues();
        ContentValues cvWorkout = new ContentValues();
        sqLiteDatabase.replace(TABLE_ROUTINE, null, cv);
        for (int numberOfExercise = 0; numberOfExercise < exercises.size(); numberOfExercise++) {

            cvExercise.put(KEY_EXERCISE, exercises.get(numberOfExercise).toString());
            sqLiteDatabase.replace(TABLE_EXERCISE, null, cvExercise);

            cvWorkout.put(KEY_EXERCISE, exercises.get(numberOfExercise).getExercisename());

            cvWorkout.put(KEY_ROUTINE, routine.getRoutineName().toString());
            cvWorkout.put(KEY_SET, exercises.get(numberOfExercise).getSet());
            cvWorkout.put(KEY_REP, exercises.get(numberOfExercise).getRep());
            sqLiteDatabase.replace(TABLE_WORKOUT, null, cvWorkout);
        }
        sqLiteDatabase.close();
    }


    //select calories on current date
    public int selectNutrition() {
        int Calorie = 0;
        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();
        //select by date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                " yyyy-MM-dd");
        String dates = sdf.format(currentDate);
        Cursor cursor = sqLiteDatabase.query(TABLE_NUTRISION, new String[]{KEY_CALORIES
                }, KEY_NU_DATE + "=?",
                new String[]{dates.toString()}, null, null, null, null);
        if (cursor != null) { //if cursor is not null get value and return the value.
            if (cursor.moveToFirst()) {
                Calorie = Integer.parseInt(cursor.getString(0));
            }
            return Calorie;
        } else { //if nothing on calories table in current date then return 0
            return 0;
        }

    }

    //add Calories to Nutrition Table
    public void addNutrition(int cal) {
        Date currentDate = new Date();
        //select by date
        int dailycalory = selectNutrition();
        cal += dailycalory;
        //if there is a date-> get value->add the users value
        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat(
                " yyyy-MM-dd");
        String dates = sdf.format(currentDate);
        ContentValues cv = new ContentValues();
        cv.put(KEY_NU_DATE, dates.toString());
        cv.put(KEY_CALORIES, cal);
        sqLiteDatabase.replace(TABLE_NUTRISION, null, cv);
    }

    //add value to progress table
    public long addProgress(Progress progress) {
        database = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(currentDate);

        cv.put(KEY_PROG_DATE, newDate);
        cv.put(KEY_WEIGHT, progress.getWeight());
        cv.put(KEY_BMI, progress.getBmi());
        cv.put(KEY_PROG_CALORIES, progress.getCalories());
        cv.put(KEY_BODYFAT, progress.getBodyfat());
        cv.put(KEY_PROG_ROUTINE, progress.getRoutineName());

        long id = database.replace(TABLE_PROGRESS, null, cv);

        database.close();
        return id;
    }

    //update progress (update completed routine name on current date)
    public long updateProgress(String routineName){
        database = openHelper.getWritableDatabase();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(currentDate);

        ContentValues cv = new ContentValues();
        cv.put(KEY_PROG_ROUTINE, routineName);

        //set where clause
        String where = KEY_PROG_DATE+ "=?";
        String[] whereArgs = new String[] {date};

        //update table and
        long rowAffected = database.update(TABLE_PROGRESS, cv, where, whereArgs);
        return rowAffected;
    }

    //get progress Table
    public ArrayList<Progress> getProgress() throws ParseException {
        ArrayList<Progress> progressList = new ArrayList();

        database = openHelper.getReadableDatabase();
        //get all progress order by date
        Cursor cursor = database.query(TABLE_PROGRESS, null, null, null, null, null, KEY_PROG_DATE);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (cursor.moveToNext()) {
            String progDate = cursor.getString(cursor.getColumnIndex(KEY_PROG_DATE));
            double progWeight = cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT));
            double progBmi = cursor.getDouble(cursor.getColumnIndex(KEY_BMI));
            double progBodyFat = cursor.getDouble(cursor.getColumnIndex(KEY_BODYFAT));
            String progRoutine = cursor.getString(cursor.getColumnIndex(KEY_PROG_ROUTINE));

            Progress newProg = new Progress(sdf.parse(progDate), progWeight, progBmi, 1.0, progBodyFat, progRoutine);

            progressList.add(newProg);
        }
        cursor.close();
        database.close();

        return progressList;
    }


    //create DB
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory o, int dbVersion) {
            super(context, dbName, o, dbVersion);
        }

        //onCreate create all tables
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(CREATE_ROUTINE_TABLE);
            sqLiteDatabase.execSQL(CREATE_EXERCISE_TABLE);
            sqLiteDatabase.execSQL(CREATE_WORKOUT_TABLE);
            sqLiteDatabase.execSQL(CREATE_NUTRITION_TABLE);
            sqLiteDatabase.execSQL(CREATE_PROGRESS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            onCreate(sqLiteDatabase);
        }
    }

}
