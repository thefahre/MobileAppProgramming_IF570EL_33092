package com.example.week09_33092;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {

    private final LayoutInflater mInflater;
    private List<Mahasiswa> listMahasiswa;
    MahasiswaListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MahasiswaListAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mhs_item_layout,parent,false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaListAdapter.MahasiswaViewHolder holder, int position) {
        if(listMahasiswa!=null){
            Mahasiswa mhs = listMahasiswa.get(position);
            holder.tvNim.setText(mhs.getNim());
            holder.tvName.setText(mhs.getName());
        }else{
            holder.tvNim.setText("No Student Registered");
        }

    }

    @Override
    public int getItemCount() {
        if(listMahasiswa!=null){
            return  listMahasiswa.size();
        }else{
        return 0;
        }
    }

    public Mahasiswa getMahasiswaAtPosition(int position){
        return listMahasiswa.get(position);
    }

    void setListMahasiswa(List<Mahasiswa> mhss){
        listMahasiswa = mhss;
        notifyDataSetChanged();
    }

    public class MahasiswaViewHolder extends  RecyclerView.ViewHolder{
        private final TextView tvNim;
        private final TextView tvName;

        public MahasiswaViewHolder(@NonNull View itemView){
            super(itemView);
            tvNim = itemView.findViewById(R.id.NimItems);
            tvName = itemView.findViewById(R.id.NameItems);
        }
    }
}
