package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class introAppActivity extends AppCompatActivity {
    ImageButton BackButton;
    ImageButton ShoulderButton;
    ImageButton ChestButton;
    ImageButton ArmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_app);
        BackButton = (ImageButton) findViewById(R.id.BackButton);
        ShoulderButton = (ImageButton) findViewById(R.id.ShoulderButton);
        ChestButton = (ImageButton) findViewById(R.id.ChestButton);
        ArmButton = (ImageButton) findViewById(R.id.ArmButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               BackButtonAct();
            }
        });
        ShoulderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoulderButtonAct();
            }
        });
        ChestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChestButtonAct();
            }
        });
        ArmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArmButtonAct();
            }
        });
    }
    public void BackButtonAct () {
        Intent intent = new Intent(introAppActivity.this, BackActivity.class);
        startActivity(intent);
    }
    public void ShoulderButtonAct () {
        Intent intent = new Intent(introAppActivity.this, ShoulderActivity.class);
        startActivity(intent);
    }
    public void ChestButtonAct () {
        Intent intent = new Intent(introAppActivity.this, ChestActivity.class);
        startActivity(intent);
    }
    public void ArmButtonAct () {
        Intent intent = new Intent(introAppActivity.this, ArmActivity.class);
        startActivity(intent);
    }
}