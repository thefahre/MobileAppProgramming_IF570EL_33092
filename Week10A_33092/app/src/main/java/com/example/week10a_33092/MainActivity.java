package com.example.week10a_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE="cuurentText";
    private TextView mTvStatus;
    private ProgressBar mPBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvStatus = findViewById(R.id.tvStatus);
        mPBar = findViewById(R.id.pBar);
        mPBar.setMax(100);
        if(savedInstanceState != null){
            mTvStatus.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void StartTask(View view){
        mTvStatus.setText("Ready to Start");
        new SimpleAsyncTask().execute((int) (Math.random()*50)+10);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,mTvStatus.getText().toString());
    }

    private class SimpleAsyncTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute(){
            mPBar.setVisibility(View.VISIBLE);
            mPBar.setProgress(0);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int n = integers[0].intValue();
            int s = 0;
            try{
                s = n*100;
                for (int i = 0;i<n;i++){
                    Thread.sleep(200);
                    publishProgress((int)((100*i)/(float)n));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "Active again after" + s + "mSeconds";
        }

        @Override
        protected void onProgressUpdate(Integer... progress){
            mTvStatus.setText("Progress=" +progress[0]+"%");
            mPBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result){
            mTvStatus.setText(result);
            mPBar.setVisibility(View.INVISIBLE);
        }
    }
}