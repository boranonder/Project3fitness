package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button ExcersizesButton;
    Button NutritionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExcersizesButton = (Button) findViewById(R.id.ExcersizesButton);
        NutritionButton = (Button) findViewById(R.id.NutritionButton);
        ExcersizesButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExcersizeButtonAct();
            }
        });

        NutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NutritionButtonAct();
            }
        });
    }


    public void ExcersizeButtonAct () {
            Intent intent = new Intent(MainActivity.this, introAppActivity.class);
            startActivity(intent);
        }
        public void NutritionButtonAct () {
            Intent intent = new Intent(MainActivity.this, NutritionCalcActivity.class);
            startActivity(intent);
        }
    }
