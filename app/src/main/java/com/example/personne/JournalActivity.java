package com.example.personne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.text.SimpleDateFormat;

public class JournalActivity extends AppCompatActivity {

    EditText raw_entry;
    Button commit_btn;
    String entry;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        raw_entry = findViewById(R.id.journal_entry);
        commit_btn = findViewById(R.id.button3);

        Button pass = findViewById(R.id.button4);

        commit_btn.setOnClickListener(v -> {
            entry = raw_entry.getText().toString();
            Date raw_date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatter.format(raw_date);
            boolean validate = true;

            if(db.checkEntry(date)){

                Boolean check_update = db.updateEntry(entry, date);
                if(check_update){
                    Toast.makeText(JournalActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ReviewActivity.class);
                    startActivity(intent);
                } else {
                    validate = false;
                }
            } else {

                Boolean check_insert = db.insertEntry(entry, date);
                if(check_insert){
                    Toast.makeText(JournalActivity.this, "Entry Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ReviewActivity.class);
                    startActivity(intent);
                } else {
                    validate = false;
                }
            }

            if(!validate){
                Toast.makeText(JournalActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        });

//        // Hardcoded data
//        if (!db.checkEntry("01.01.2020")){
//            db.insertEntry("Demo demo demo demo demo", "01.01.2020");
//        }
//
//        if (!db.checkEntry("03.09.2001")){
//            db.insertEntry("Lorem ipsum deux", "03.09.2001");
//        }

        pass.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            startActivity(intent);
        });
    }
}