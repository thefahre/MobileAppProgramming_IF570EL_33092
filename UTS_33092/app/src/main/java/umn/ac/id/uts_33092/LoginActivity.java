package umn.ac.id.uts_33092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText namaUser;
    private Button loginNext;
    boolean validated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        namaUser = (EditText) findViewById(R.id.namaUser);
        loginNext = (Button) findViewById(R.id.loginNext);

        loginNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validated = validateInput();
                if (validated) {
                    //Ambil value string dari namaUser buat dipassthru via Intent
                    String namauser = namaUser.getText().toString();
                    Intent libraryIntent = new Intent(LoginActivity.this, LibraryActivity.class);
                    //Passing string tadi ke Activity baru via Intent
                    libraryIntent.putExtra("namauser", namauser);
                    startActivity(libraryIntent);
                }
            }
        });
    }

    private boolean validateInput() {
        if (namaUser.length() == 0) {
            namaUser.setError("Nama harus diisi.");
            return false;
        }
        return true;
    }
}