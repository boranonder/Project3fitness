package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatedActivity extends AppCompatActivity {

    TextView bmrTextView;
    TextView bmiTextView;
    private TextView resultText;
    private ImageView nutritionPlanImage;
    Button dietProgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        bmiTextView = findViewById(R.id.BMItext);
        bmrTextView = findViewById(R.id.BMRtext);
        dietProgButton = findViewById(R.id.dietProgButton);
        Intent intent = getIntent();
        if (intent != null) {
            double bmi = intent.getDoubleExtra("bmi", 0);
            double bmr = intent.getDoubleExtra("bmr", 0);
            bmiTextView.setText(String.format("Your BMI: %.2f", bmi));
            bmrTextView.setText(String.format("Your Basal Metabolic Rate is: %.2f", bmr));
            dietProgButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayResult(bmi,bmr);
                        }
                    });

        }

    }

    private void displayResult(double bmi, double bmr) {
        String bmiLabel;
        int nutritionPlanResId;

        if (bmi < 18.5) {
            bmiLabel = "Underweight";
            nutritionPlanResId = R.drawable.underweight_plan;
        } else if (bmi < 24.9) {
            bmiLabel = "Normal weight";
            nutritionPlanResId = R.drawable.normal_weight_plan;
        } else {
            bmiLabel = "Overweight";
            nutritionPlanResId = R.drawable.overweight_plan;
        }

        resultText.setText(String.format("BMI: %.1f\n%s\n\nBMR: %.1f kcal/day", bmi, bmiLabel, bmr));
        nutritionPlanImage.setImageResource(nutritionPlanResId);
    }


}