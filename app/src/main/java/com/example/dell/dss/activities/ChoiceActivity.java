package com.example.dell.dss.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.dss.R;

public class ChoiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        TextView admin = findViewById(R.id.admin);
        TextView guest = findViewById(R.id.guest);



        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
