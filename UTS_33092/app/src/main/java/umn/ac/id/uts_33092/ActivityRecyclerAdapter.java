package umn.ac.id.uts_33092;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.ViewHolder> {
    String sfxtitles[], sfxdescs[];
    Context context;
    public ActivityRecyclerAdapter(Context ct, String sfx_title[], String sfx_desc[]){
        context = ct;
        sfxtitles = sfx_title;
        sfxdescs = sfx_desc;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sfx_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.sfxTitle.setText(sfxtitles[position]);
        holder.sfxDesc.setText(sfxdescs[position]);
        holder.sfx_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DetailsActivityIntent = new Intent(context, DetailsActivity.class);
                DetailsActivityIntent.putExtra("sfxtitles", sfxtitles[position]);
                DetailsActivityIntent.putExtra("sfxdescs", sfxdescs[position]);
                context.startActivity(DetailsActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sfxdescs.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sfxTitle, sfxDesc;
        ConstraintLayout sfx_row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sfxTitle = itemView.findViewById(R.id.sfxTitle);
            sfxDesc = itemView.findViewById(R.id.sfxDesc);
            sfx_row = itemView.findViewById(R.id.sfx_row);
        }
    }
}
