package com.example.fitnessapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDataActivity extends AppCompatActivity {

    private static final String TAG = "ViewDataActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        TextView dataTextView = findViewById(R.id.dataTextView);

        // Veritabanındaki verileri çekin ve TextView'de görüntüleyin
        try (DatabaseHelper databaseHelper = new DatabaseHelper(this);
             Cursor cursor = databaseHelper.getAllData()) {

            StringBuilder dataString = new StringBuilder();

            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int heightIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_HEIGHT);
                int weightIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_WEIGHT);
                int ageIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE);
                int genderIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER);

                do {
                    int id = cursor.getInt(idIndex);
                    double height = cursor.getDouble(heightIndex);
                    double weight = cursor.getDouble(weightIndex);
                    int age = cursor.getInt(ageIndex);
                    String gender = cursor.getString(genderIndex);

                    dataString.append("ID: ").append(id)
                            .append("\nHeight: ").append(height)
                            .append("\nWeight: ").append(weight)
                            .append("\nAge: ").append(age)
                            .append("\nGender: ").append(gender)
                            .append("\n\n");
                } while (cursor.moveToNext());
            } else {
                Log.d(TAG, "Cursor is null or empty");
            }

            dataTextView.setText(dataString.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving data", e);
        }
    }
}
