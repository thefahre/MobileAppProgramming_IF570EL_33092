package com.example.week09_33092;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this,DetailActivity.class);
                startActivityForResult(addIntent,REQUEST_ADD);
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvMahasiswa);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
        mhsVM = new ViewModelProvider(this).get(MahasiswaViewModel.class);
        mhsVM.getMahasiswaList().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(List<Mahasiswa> mahasiswas) {
                adapter.setListMahasiswa(mahasiswas);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Mahasiswa mhs = adapter.getMahasiswaAtPosition(position);
                if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(MainActivity.this,"Student with NIM = "+mhs.getNim()+"Deleted",Toast.LENGTH_LONG).show();
                    mhsVM.delete(mhs);
                }else if (direction == ItemTouchHelper.RIGHT){
                    Intent editIntent = new Intent(MainActivity.this,DetailActivity.class);
                    editIntent.putExtra("MAHASISWA",mhs);
                    startActivityForResult(editIntent,REQUEST_EDIT);
                }
            }
        });
        helper.attachToRecyclerView(rv);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Mahasiswa mhs = (Mahasiswa) data.getSerializableExtra("MAHASISWA");
            if(reqCode == REQUEST_ADD){
                mhsVM.insert(mhs);
            } else if (reqCode == REQUEST_EDIT){
                mhsVM.update(mhs);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mhsVM.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}