package com.sahanmw.roadsafetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Button btnBackToUpload = findViewById(R.id.btnBackToUpload);

        btnBackToUpload.setOnClickListener(v -> {
            // Navigate back to the video upload page
            Intent intent = new Intent(InstructionsActivity.this, VideoUploadActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

