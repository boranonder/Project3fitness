package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionCalcActivity extends AppCompatActivity {
    EditText heightText;
    EditText weightText;
    Button CalculateButton;
    EditText AgeText;
    EditText GenderText;
    double bmi;
    double bmr;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritioncalc);

        dbHelper = new DatabaseHelper(this);

        heightText = findViewById(R.id.HeightText);
        weightText = findViewById(R.id.WeightText);
        AgeText = findViewById(R.id.AgeText);
        GenderText = findViewById(R.id.GenderText);
        CalculateButton = findViewById(R.id.CalculationButton);

        CalculateButton.setOnClickListener(view -> isEveryTextTrue());
    }

    public void isEveryTextTrue() {
        String ageStr = AgeText.getText().toString();
        String gender = GenderText.getText().toString().toLowerCase();
        String heightStr = heightText.getText().toString();
        String weightStr = weightText.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty() || ageStr.isEmpty() || gender.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter all information above.", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);
        double age = Double.parseDouble(ageStr);

        if (!(age > 0 && age < 120 && (gender.equals("male") || gender.equals("female")) && weight < 300 && weight > 0 && height > 0 && height < 300)) {
            Toast.makeText(getApplicationContext(), "Please enter valid information.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user data to database
        saveUserData(height, weight, age, gender);

        bmi = weight / (height / 100 * height / 100);

        if (gender.equals("male")) {
            bmr = 66.47 + (13.75 * weight) + (5 * height) - (6.76 * age);
        } else {
            bmr = 655.10 + (9.56 * weight) + (1.85 * height) - (4.68 * age);
        }

        Intent intent = new Intent(NutritionCalcActivity.this, CalculatedActivity.class);
        intent.putExtra("bmi", bmi);
        intent.putExtra("bmr", bmr);
        startActivity(intent);
    }

    private void saveUserData(double height, double weight, double age, String gender) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_HEIGHT, height);
        values.put(DatabaseHelper.COLUMN_WEIGHT, weight);
        values.put(DatabaseHelper.COLUMN_AGE, age);
        values.put(DatabaseHelper.COLUMN_GENDER, gender);

        db.insert(DatabaseHelper.TABLE_USERS, null, values);
    }
}
