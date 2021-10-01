package com.example.week4a_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvAnswer;
    private EditText etInput,etUrl;
    private Button btnSend,btnBrowse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = findViewById(R.id.textinpt);
        etUrl = findViewById(R.id.Url);
        btnSend = findViewById(R.id.buttonSend);
        btnBrowse = findViewById(R.id.buttonBrowse);
        tvAnswer = findViewById(R.id.answer);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlText = etUrl.getText().toString();
                if(urlText.isEmpty()){
                    urlText = "http://www.umn.ac.id/";
                }
                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse(urlText));
                if(browseIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(browseIntent);
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent=new Intent(MainActivity.this,SecondActivity.class);
                String inp = etInput.getText().toString();
                secondIntent.putExtra("Text from Main",inp);
                startActivityForResult(secondIntent,1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                String answer = data.getStringExtra("Answer");
                tvAnswer.setText(answer);
            }
        }
    }

}