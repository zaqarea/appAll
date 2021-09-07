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

public class AdabterStatsForMonth extends RecyclerView.Adapter<AdabterStatsForMonth.StatsMonthViewHolder> {

    private final ArrayList<ModelStats> statsArrayList;
    private final Context context;
    public static final String LECTURE = "lecture";

    public AdabterStatsForMonth(ArrayList<ModelStats> statsArrayList, Context context) {
        this.context = context;
        this.statsArrayList = statsArrayList;
    }

    @NonNull
    @Override
    public StatsMonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_details_monthly, parent, false);
        return new StatsMonthViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatsMonthViewHolder holder, int position) {
        ModelStats modelStats = statsArrayList.get(position);

//        holder.txtViewLateHoursMonth.setText(modelStats.getLateHoursOfMonth());
//        holder.txtViewTimeOutMonth.setText(modelStats.getExsitDate());
//        holder.txtViewEntryTimeMonth.setText(modelStats.getEntranceDate());

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
    static class StatsMonthViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewLateHoursMonth, txtViewTimeOutMonth, txtViewEntryTimeMonth;


        public StatsMonthViewHolder(@NonNull View itemView) {
            super(itemView);
//            txtViewDateMonth = itemView.findViewById(R.id.txtViewDateMonth);
//            txtViewDay = itemView.findViewById(R.id.txtViewDay);
            txtViewLateHoursMonth = itemView.findViewById(R.id.txtViewLateHoursMonth);
            txtViewTimeOutMonth = itemView.findViewById(R.id.txtViewTimeOutMonth);
            txtViewEntryTimeMonth = itemView.findViewById(R.id.txtViewEntryTimeMonth);
        }
    }

}
