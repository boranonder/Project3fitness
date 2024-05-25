package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatedActivity extends AppCompatActivity {

    TextView bmrTextView;
    TextView bmiTextView;
    Button dietProgButton;

    private double bmi;
    private double bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);

        bmiTextView = findViewById(R.id.BMItext);
        bmrTextView = findViewById(R.id.BMRtext);
        dietProgButton = findViewById(R.id.dietProgButton);

        Intent intent = getIntent();
        if (intent != null) {
            bmi = intent.getDoubleExtra("bmi", 0);
            bmr = intent.getDoubleExtra("bmr", 0);
            bmiTextView.setText(String.format("Your BMI: %.2f", bmi));
            bmrTextView.setText(String.format("Your Basal Metabolic Rate is: %.2f", bmr));
        }

        dietProgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDietPlan();
            }
        });
    }

    private void openDietPlan() {
        Intent intent = new Intent(CalculatedActivity.this, DietPlanActivity.class);

        int nutritionPlanResId = -1;
        if (bmi < 18.5) {
            nutritionPlanResId = R.drawable.underweight_diet_plan;
        } else if (bmi < 24.9) {
            nutritionPlanResId = R.drawable.normal_weight_diet_plan;
        } else if (bmi >= 24.9) {
            nutritionPlanResId = R.drawable.overweight_diet_plan;
        }

        if (nutritionPlanResId != -1) {
            intent.putExtra("nutritionPlanResId", nutritionPlanResId);
            startActivity(intent);
        } else {

        }
    }
}



