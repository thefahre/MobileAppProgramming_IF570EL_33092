package com.example.week09_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private EditText etNim, etName, etEmail, etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etNim = findViewById(R.id.etNim);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        Intent intent = getIntent();
        if(intent.hasExtra("MAHASISWA")){
            Mahasiswa mhs = (Mahasiswa) intent.getSerializableExtra("MAHASISWA");
            etNim.setText(mhs.getNim());
            etName.setText(mhs.getName());
            etEmail.setText(mhs.getEmail());
            etPhone.setText(mhs.getPhoneNumber());
            etNim.setEnabled(false);
        }else{
            etNim.setEnabled(true);
        }
    }

    public void SaveData(View view){
        String mNIM = etNim.getText().toString();
        String mName = etName.getText().toString();
        String mEmail = etEmail.getText().toString();
        String mPhone = etPhone.getText().toString();
        if(mNIM.length() <= 0 || mPhone.length()<= 0){
            Toast.makeText(this,"All field need to be filled",Toast.LENGTH_LONG).show();
        }else{
            Intent reIntent = new Intent();
            Mahasiswa mhs = new Mahasiswa(mNIM,mName,mEmail,mPhone);
            reIntent.putExtra("MAHASISWA",mhs);
            setResult(RESULT_OK,reIntent);
            finish();
        }
    }
    public void Cancel(View view){
        Intent reIntent = new Intent();
        setResult(RESULT_CANCELED,reIntent);
        finish();
    }
}