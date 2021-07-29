package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentsQROrg.DetailsStudents;
import com.example.StudentsQROrg.Model.ModelCourse;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

public class AdabterCourse extends RecyclerView.Adapter<AdabterCourse.CoursViewHolder> {

    private final ArrayList<ModelCourse> coursesArrayList;
    private final Context context;
    public static final String COURSE = "course";

    public AdabterCourse(ArrayList<ModelCourse> coursesArrayList, Context context) {
            this.context = context;
            this.coursesArrayList = coursesArrayList;
            }

    @NonNull
    @Override
    public CoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cours, parent, false);
            return new CoursViewHolder(view);
            }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CoursViewHolder holder, int position) {
        ModelCourse modelCourse = coursesArrayList.get(position);

        holder.courseName.setText(modelCourse.getCourseName());
        holder.courseDateStart.setText("وقت بداية الدورة:" + "  " + modelCourse.getCourseDateStart());
        holder.courseDateEnd.setText("وقت نهاية الدورة:" + "  " + modelCourse.getCourseDateEnd());
        holder.coachName.setText("المدرب:" + "  " + modelCourse.getCoachName());
        holder.numberHours.setText("عدد ساعات الدورة:" + "  " + modelCourse.getNumberHours());
       // holder.courseContent.setText(modelCourse.getCourseName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsStudents.class);
                intent.putExtra(COURSE, modelCourse);
                context.startActivity(intent);
            }
        });

    //        holder.courseName.setText("اسم الدورة:" + "  " + modelCourse.getCourseName());
    //        holder.courseTime.setText("اوقات الدورة:" + "  " + modelCourse.getCourseDateStart());
    //        holder.coachName.setText("اسم المدرب:" + "  " + modelCourse.getCoachName());
    //        holder.numberHours.setText("عدد الساعات:" + "  " + modelCourse.getNumberHours());
    //        holder.courseContent.setText("محاور الدورة:" + "  " + modelCourse.getCourseContent());

//    holder.itemView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//            Intent intent = new Intent(context, Lecture.class);
//            intent.putExtra(COURSE, modelCourse);
//            context.startActivity(intent);
//            }
//            });

            }

    @Override
    public int getItemCount() {
            return coursesArrayList.size();
            }

    //Class Recycler HolderView
    static class CoursViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView courseDateStart;
        private TextView courseDateEnd;
        private TextView coachName;
        private TextView numberHours;
        private TextView courseContent;


        public CoursViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.txtViewCourseName);
            courseDateStart = itemView.findViewById(R.id.txtViewCourseDateStart);
            courseDateEnd = itemView.findViewById(R.id.txtViewCourseDateEnd);
            coachName = itemView.findViewById(R.id.txtViewCoachName);
            numberHours = itemView.findViewById(R.id.txtViewNumberHours);
            courseContent = itemView.findViewById(R.id.txtViewCourseContent);
        }
    }
}