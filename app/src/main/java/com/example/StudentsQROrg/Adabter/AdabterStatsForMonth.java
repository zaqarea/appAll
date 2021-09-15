package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentsQROrg.Model.ModelStats;
import com.example.StudentsQROrg.R;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class AdabterStatsForMonth extends RecyclerView.Adapter<AdabterStatsForMonth.StatsMonthViewHolder> {

    private final ArrayList<ModelStats> statsArrayList;
    private final Context context;
    public static final String LECTURE = "lecture";
    String currentMonth;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public AdabterStatsForMonth(ArrayList<ModelStats> statsArrayList, Context context) {
        this.context = context;
        this.statsArrayList = statsArrayList;
        OffsetDateTime currentDate = OffsetDateTime.now(ZoneOffset.UTC);
        currentMonth = currentDate.getMonth() + " /" + currentDate.getMonthValue() + " /" + currentDate.getYear() + "";
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

        holder.txtViewAbsenceDayMonth.setText(modelStats.getPresenceDaysOfMonth());
        holder.txtViewDayOutMonth.setText(modelStats.getAbsenceDaysOfMonth());
        holder.txtViewLateHoursMonth.setText(modelStats.getLateHoursOfMonth());
        holder.txtViewDateMonth.setText(currentMonth);

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
        TextView txtViewAbsenceDayMonth, txtViewDayOutMonth, txtViewLateHoursMonth, txtViewDateMonth;

        public StatsMonthViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewAbsenceDayMonth = itemView.findViewById(R.id.txtViewAbsenceDayMonth);
            txtViewDayOutMonth = itemView.findViewById(R.id.txtViewDayOutMonth);
            txtViewLateHoursMonth = itemView.findViewById(R.id.txtViewLateHoursMonth);
            txtViewDateMonth = itemView.findViewById(R.id.txtViewDateMonth);
        }
    }

}
