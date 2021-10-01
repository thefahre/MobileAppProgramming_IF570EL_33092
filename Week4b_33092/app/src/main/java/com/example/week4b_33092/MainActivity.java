package com.example.week4b_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btp1, btp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btp1 = findViewById(R.id.main_btn_change_1);
        btp2 = findViewById(R.id.main_btn_change_2);
        btp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPage1 = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(toPage1);
            }
        });
        btp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPage2 = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(toPage2);
            }
        });
    }
}