package com.example.personne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LandingActivity extends AppCompatActivity {

    Button writebtn, readbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        writebtn = findViewById(R.id.button);
        readbtn = findViewById(R.id.button2);

        writebtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, JournalActivity.class);
            startActivity(intent);
        });

        readbtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            startActivity(intent);
        });

    }
}