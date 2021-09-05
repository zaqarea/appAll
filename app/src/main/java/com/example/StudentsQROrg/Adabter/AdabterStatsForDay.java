package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentsQROrg.Model.ModelStats;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

public class AdabterStatsForDay extends RecyclerView.Adapter<AdabterStatsForDay.StatsDayViewHolder> {

    private final ArrayList<ModelStats> statsArrayList;
    private final Context context;
    public static final String LECTURE = "lecture";

    public AdabterStatsForDay(ArrayList<ModelStats> statsArrayList, Context context) {
        this.context = context;
        this.statsArrayList = statsArrayList;
    }

    @NonNull
    @Override
    public StatsDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_work_for_day, parent, false);
        return new StatsDayViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatsDayViewHolder holder, int position) {
        ModelStats modelStats = statsArrayList.get(position);

        if (!modelStats.getIsExsist().equals("0")){
            holder.txtViewEntryTime.setText(modelStats.getEntranceDate());
            holder.txtViewTimeOut.setText(modelStats.getExsitDate());
            holder.txtViewStatus.setText("متواجد");
            holder.txtViewStatus.setTextColor(Color.GREEN);
        }else {
            holder.txtViewStatus.setText("غائب");
            holder.txtViewStatus.setTextColor(Color.RED);
        }

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
    static class StatsDayViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewDateMonth, txtViewDay, txtViewEntryTime, txtViewTimeOut, txtViewStatus;
      //  ImageView imgViewRowNotification;

        public StatsDayViewHolder(@NonNull View itemView) {
            super(itemView);
//            txtViewDateMonth = itemView.findViewById(R.id.txtViewDateMonth);
//            txtViewDay = itemView.findViewById(R.id.txtViewDay);
            txtViewEntryTime = itemView.findViewById(R.id.txtViewEntryTimeWork);
            txtViewTimeOut = itemView.findViewById(R.id.txtViewTimeOutWork);
            txtViewStatus = itemView.findViewById(R.id.txtViewStatusWork);
        }
    }

}
