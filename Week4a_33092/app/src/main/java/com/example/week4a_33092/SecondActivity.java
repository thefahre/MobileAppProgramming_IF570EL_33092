package com.example.week4a_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tvReceivText;
    private EditText etAnswer;
    private Button btnReplyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvReceivText = findViewById(R.id.receivText);
        etAnswer = findViewById(R.id.returnText);
        btnReplyText = findViewById(R.id.replyText);
        Intent mainIntent = getIntent();
        String receivMsg = mainIntent.getStringExtra("Text from Main");
        tvReceivText.setText(receivMsg);
    }
    public void SendBack(View view){
        String answer = etAnswer.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Answer",answer);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}