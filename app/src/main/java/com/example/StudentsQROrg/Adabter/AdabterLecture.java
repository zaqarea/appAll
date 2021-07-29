package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentsQROrg.Model.ModelLecture;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

public class AdabterLecture extends RecyclerView.Adapter<AdabterLecture.LectureViewHolder> {

    private final ArrayList<ModelLecture> lectureArrayList;
    private final Context context;
    public static final String LECTURE = "lecture";

    public AdabterLecture(ArrayList<ModelLecture> lectureArrayList, Context context) {
        this.context = context;
        this.lectureArrayList = lectureArrayList;
    }

    @NonNull
    @Override
    public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture, parent, false);
        return new LectureViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LectureViewHolder holder, int position) {
        ModelLecture modelLecture = lectureArrayList.get(position);

        holder.lectureTitleRow.setText(modelLecture.getLectureTitle());
        holder.courseName.setText(modelLecture.getCourseName());
        if (modelLecture.getPresenceTime() != null) {
            holder.presenceTimeRow.setText(modelLecture.getPresenceTime());
        }else {
            holder.presenceTimeRow.setText("لم احضر بعد");
        }
        holder.startTime.setText(modelLecture.getStartTime());
        holder.endTime.setText(modelLecture.getEndTime());
       // holder.lagRow.setText("التأخير:" + "  " + modelLecture.getl());

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
        return lectureArrayList.size();
    }

    //Class Recycler HolderView
    static class LectureViewHolder extends RecyclerView.ViewHolder {
        private TextView lectureTitleRow, courseName, presenceTimeRow, startTime, endTime, lagRow;
        
        public LectureViewHolder(@NonNull View itemView) {
            super(itemView);
            lectureTitleRow = itemView.findViewById(R.id.txtViewLectureTitleRow);
            courseName = itemView.findViewById(R.id.txtViewCourseName);
            presenceTimeRow = itemView.findViewById(R.id.txtViewPresenceTimeRow);
            startTime = itemView.findViewById(R.id.txtViewstartTimeRow);
            endTime = itemView.findViewById(R.id.txtViewendTimeRow);
//            lagRow = itemView.findViewById(R.id.txtViewLagRow);
        }
    }



}
