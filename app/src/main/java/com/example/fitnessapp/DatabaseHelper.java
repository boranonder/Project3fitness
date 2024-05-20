package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 1;

    // Users Table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_BMI = "bmi";
    private static final String COLUMN_BMR = "bmr";

    // Exercises Table
    private static final String TABLE_EXERCISES = "exercises";
    private static final String COLUMN_EXERCISE_ID = "exercise_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    // User Exercises Table
    private static final String TABLE_USER_EXERCISES = "user_exercises";
    private static final String COLUMN_USER_EXERCISE_ID = "user_exercise_id";
    private static final String COLUMN_USER_ID_FK = "user_id";
    private static final String COLUMN_EXERCISE_ID_FK = "exercise_id";
    private static final String COLUMN_EXERCISE_DATE = "exercise_date";

    // Nutrition Plans Table
    private static final String TABLE_NUTRITION_PLANS = "nutrition_plans";
    private static final String COLUMN_NUTRITION_PLAN_ID = "nutrition_plan_id";
    private static final String COLUMN_PLAN_NAME = "plan_name";
    private static final String COLUMN_PLAN_DESCRIPTION = "plan_description";
    private static final String COLUMN_PLAN_IMAGE = "plan_image";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " ("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
            + COLUMN_PASSWORD + " TEXT NOT NULL, "
            + COLUMN_BMI + " REAL, "
            + COLUMN_BMR + " REAL, "
            + COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

    private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE " + TABLE_EXERCISES + " ("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT"
            + ")";

    private static final String CREATE_TABLE_USER_EXERCISES = "CREATE TABLE " + TABLE_USER_EXERCISES + " ("
            + COLUMN_USER_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID_FK + " INTEGER, "
            + COLUMN_EXERCISE_ID_FK + " INTEGER, "
            + COLUMN_EXERCISE_DATE + " DATE, "
            + "FOREIGN KEY (" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "), "
            + "FOREIGN KEY (" + COLUMN_EXERCISE_ID_FK + ") REFERENCES " + TABLE_EXERCISES + "(" + COLUMN_EXERCISE_ID + ")"
            + ")";

    private static final String CREATE_TABLE_NUTRITION_PLANS = "CREATE TABLE " + TABLE_NUTRITION_PLANS + " ("
            + COLUMN_NUTRITION_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PLAN_NAME + " TEXT NOT NULL, "
            + COLUMN_PLAN_DESCRIPTION + " TEXT, "
            + COLUMN_PLAN_IMAGE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_USER_EXERCISES);
        db.execSQL(CREATE_TABLE_NUTRITION_PLANS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUTRITION_PLANS);
        onCreate(db);
    }

    // Kullanıcı ekleme metodu
    public long addUser(String email, String password, float bmi, float bmr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_BMI, bmi);
        values.put(COLUMN_BMR, bmr);

        long userId = db.insert(TABLE_USERS, null, values);
        db.close();
        return userId;
    }

    // Kullanıcı bilgilerini alma metodu
    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_EMAIL + "=?", new String[]{email}, null, null, null);
        return cursor;
    }
}
