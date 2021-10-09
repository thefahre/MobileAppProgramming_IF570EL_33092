package umn.ac.id.uts_33092;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class LibraryActivity extends AppCompatActivity {
    RecyclerView ActivityRecycler;
    String sfx_title[], sfx_desc[];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            sfx_title = getResources().getStringArray(R.array.sfx_title);
            sfx_desc = getResources().getStringArray(R.array.sfx_desc);
            String namauser;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_library);
            Intent libraryIntent = getIntent();
            namauser = libraryIntent.getStringExtra("namauser");
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(namauser);
            Toast.makeText(LibraryActivity.this, "Selamat datang, "+namauser+"!", Toast.LENGTH_SHORT).show();
            ActivityRecycler = findViewById(R.id.ActivityRecycler);
            ActivityRecyclerAdapter adapter = new ActivityRecyclerAdapter(this, sfx_title, sfx_desc);
            ActivityRecycler.setAdapter(adapter);
            ActivityRecycler.setLayoutManager(new LinearLayoutManager(this));
        }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_library, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuProfile:
                Intent profileIntent = new Intent(LibraryActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            case R.id.homeProfile:
                Intent homeIntent = new Intent(LibraryActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}