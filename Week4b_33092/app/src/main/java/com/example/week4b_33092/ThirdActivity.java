package com.example.week4b_33092;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction =fmanager.beginTransaction();

        Fragment firstFragment = new FirstFragment();
        ftransaction.replace(R.id.third_activity_fragment_1,firstFragment);

        Fragment secondFragment = new SecondFragment();
        ftransaction.replace(R.id.third_activity_fragment_2,secondFragment);
        ftransaction.commit();
    }
}