package umn.ac.id.uts_33092;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    TextView sfxtitle, sfxdesc;
    String title, desc;
    Button sfxPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);

        sfxtitle = findViewById(R.id.sfxTitleDetails);
        sfxdesc = findViewById(R.id.sfxDescDetails);
        sfxPlayButton = findViewById(R.id.sfxPlayButton);
        setSupportActionBar(toolbar);
        //Spawn back button, h
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra("sfxtitles") && getIntent().hasExtra("sfxdescs")) {
            title = getIntent().getStringExtra("sfxtitles");
            desc = getIntent().getStringExtra("sfxdescs");
            getSupportActionBar().setTitle(title);
            MediaPlayer player;
            //MediaPlayer player = MediaPlayer.create(this, R.raw.tuturu);

            switch (title) {
                case "Tuturu":
                    player = MediaPlayer.create(this, R.raw.tuturu);
                    sfxPlayButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.start();
                        }
                    });
                    break;
                case "Amogus":
                    player = MediaPlayer.create(this, R.raw.amogus);
                    sfxPlayButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.start();
                        }
                    });
                    break;
                case "Pain Peko":
                    player = MediaPlayer.create(this, R.raw.painpeko);
                    sfxPlayButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.start();
                        }
                    });
                    break;
                default:
                    sfxPlayButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(DetailsActivity.this, "Error! No matching SFX.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT);
            getSupportActionBar().setTitle("Error.");
        }
    }

    private void setData() {
        sfxtitle.setText(title);
        sfxdesc.setText(desc);
    }

    @Override
    //Make the back button, you know, actually works
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}