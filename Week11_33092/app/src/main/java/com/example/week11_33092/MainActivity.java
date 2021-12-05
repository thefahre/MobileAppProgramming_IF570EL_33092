package com.example.week11_33092;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week11_33092.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<PostModel> data;
    RecyclerView rvPosts;
    PostDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        PostServices postServices = DataRepository.create();
        postServices.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()) {
                    data = response.body();
                    adapter = new PostDataAdapter(getApplicationContext(), data);
                    rvPosts.setAdapter(adapter);
                    rvPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to get posts.Unknown API error.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to get posts. Check internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }
}