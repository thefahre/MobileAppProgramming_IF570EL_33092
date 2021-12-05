package com.example.week10b_33092;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void> {

    private TextView mTvStatus;
    private ProgressBar mPBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvStatus = findViewById(R.id.tvStatus);
        mPBar = findViewById(R.id.pBar);
        mPBar.setMax(100);
    }

    public void StartTask(View view){
        if(!LoaderManager.getInstance(this).hasRunningLoaders()){
            LoaderManager.getInstance(this).initLoader(0,(Bundle) null,this);

        }
    }

    @NonNull
    @Override
    public Loader<Void> onCreateLoader(int id, @Nullable Bundle args) {
        AsyncTaskLoader<Void> asyncTaskLoader = new ExampleLoader(this,(int)(Math.random()*50)+10,this);
        asyncTaskLoader.forceLoad();
        return asyncTaskLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Void> loader, Void data) {
        LoaderManager.getInstance(this).destroyLoader(0);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Void> loader) {
    }

    static class ExampleLoader extends AsyncTaskLoader<Void>{

        static WeakReference<MainActivity> mActivity;
        int mCount = 0;

        public ExampleLoader(@NonNull Context context, int n, MainActivity main){
            super(context);
            mCount=n;
            mActivity = new WeakReference<MainActivity>(main);
        }

        @Nullable
        @Override
        public Void loadInBackground() {
            try{
                for(int i = 0;i<=mCount;i++){
                    Thread.sleep(200);
                    final int progress = ((int) ((100+1)/(float) mCount));
                    if(mActivity.get() != null){
                        mActivity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(progress<100){
                                    mActivity.get().mPBar.setProgress(progress);
                                    mActivity.get().mTvStatus.setText("Progress = "+progress+"%");
                                }else{
                                    mActivity.get().mPBar.setProgress(100);
                                    mActivity.get().mTvStatus.setText("Process Finished after "+mCount+"msec");
                                }
                            }
                        });
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            return null;
        }
    }
}