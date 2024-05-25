package com.example.fitnessapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DietPlanActivity extends AppCompatActivity {

    private ImageView nutritionPlanImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        nutritionPlanImage = findViewById(R.id.nutritionPlanImage);

        int nutritionPlanResId = getIntent().getIntExtra("nutritionPlanResId", -1);
        if (nutritionPlanResId != -1) {
            nutritionPlanImage.setImageResource(nutritionPlanResId);
        } else {
        }
    }
}


