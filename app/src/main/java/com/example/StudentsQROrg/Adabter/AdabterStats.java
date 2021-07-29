package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.StudentsQROrg.Model.ModelNotifications;
import com.example.StudentsQROrg.Model.ModelStats;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

public class AdabterStats extends RecyclerView.Adapter<AdabterStats.StatsViewHolder> {

    private ArrayList<ModelStats> statsArrayList;
    private final Context context;
    public static final String LECTURE = "lecture";

    public AdabterStats(ArrayList<ModelStats> statsArrayList, Context context) {
        this.context = context;
        this.statsArrayList = statsArrayList;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stats, parent, false);
        return new StatsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        ModelStats modelStats = statsArrayList.get(position);

        holder.txtViewDateMonth.setText(modelStats.getDateMonth());
        holder.txtViewDay.setText(modelStats.getDay());
        holder.txtViewEntryTime.setText(modelStats.getEntryTime());
        holder.txtViewTimeOut.setText(modelStats.getOutTime());
        holder.txtViewDelay.setText(modelStats.getDelay());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ListMyPresence.class);
//                intent.putExtra(LECTURE, modelLecture);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return statsArrayList.size();
    }

    //Class Recycler HolderView
    static class StatsViewHolder extends RecyclerView.ViewHolder {
        private TextView txtViewDateMonth, txtViewDay, txtViewEntryTime, txtViewTimeOut, txtViewDelay;
      //  ImageView imgViewRowNotification;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewDateMonth = itemView.findViewById(R.id.txtViewDateMonth);
            txtViewDay = itemView.findViewById(R.id.txtViewDay);
            txtViewEntryTime = itemView.findViewById(R.id.txtViewEntryTime);
            txtViewTimeOut = itemView.findViewById(R.id.txtViewTimeOut);
            txtViewDelay = itemView.findViewById(R.id.txtViewDelay);
        }
    }

}
