package com.example.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionCalcActivity extends AppCompatActivity {
    EditText heightText;
    EditText weightText;
    Button CalculateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritioncalc);
        heightText = findViewById(R.id.HeightText);
        weightText = findViewById(R.id.WeightText);
        CalculateButton = findViewById(R.id.CalculationButton);
        CalculateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculateBMI();
                    }
                });
    }
    private void calculateBMI() {
        String heightStr = heightText.getText().toString();
        String weightStr = weightText.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);

        double bmi = CalculationBMI(height, weight);

        Toast.makeText(getApplicationContext(), "BMI: " + bmi, Toast.LENGTH_SHORT).show();
    }

    public double CalculationBMI(double height, double weight) {
        return weight / (height * height);
    }
}