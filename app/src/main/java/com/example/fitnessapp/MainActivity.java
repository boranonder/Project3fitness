package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button ExercisesButton;
    Button NutritionButton;
    Button ViewDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExercisesButton = findViewById(R.id.ExercisesButton);
        NutritionButton = findViewById(R.id.NutritionButton);
        ViewDataButton = findViewById(R.id.ViewDataButton);

        ExercisesButton.setOnClickListener(view -> ExercisesButtonAct());
        NutritionButton.setOnClickListener(view -> NutritionButtonAct());
        ViewDataButton.setOnClickListener(view -> ViewDataButtonAct());
    }

    public void ExercisesButtonAct() {
        Intent intent = new Intent(MainActivity.this, introAppActivity.class);
        startActivity(intent);
    }

    public void NutritionButtonAct() {
        Intent intent = new Intent(MainActivity.this, NutritionCalcActivity.class);
        startActivity(intent);
    }

    public void ViewDataButtonAct() {
        Intent intent = new Intent(MainActivity.this, ViewDataActivity.class);
        startActivity(intent);
    }
}
