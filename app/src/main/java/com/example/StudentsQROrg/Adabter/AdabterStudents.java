package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentsQROrg.Model.ModelStudents;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

public class AdabterStudents extends RecyclerView.Adapter<AdabterStudents.StudentsViewHolder> {

    ArrayList<ModelStudents> studentsArrayList;
    Context context;

    public AdabterStudents(ArrayList<ModelStudents> studentsArrayList, Context context) {
        this.studentsArrayList = studentsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_students, parent, false);
        return new StudentsViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        ModelStudents modelStudents = studentsArrayList.get(position);

        holder.txtStudentName.setText(modelStudents.getStudentName());
        holder.txtIsActive.setText(modelStudents.getIsActive());
        holder.txtCurrentTime.setText(modelStudents.getStudentCurrentTime());
//        SharedPreferences sp4 = context.getSharedPreferences(PREF_FILE_NAME2, Context.MODE_PRIVATE);
//        String sp = sp4.getString(modelStudents.getCompId(), "false");
//
//        if (sp.equals("true")){
//            holder.btnRate.setEnabled(false);
//        }


    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder {
        TextView txtStudentName, txtIsActive, txtCurrentTime;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStudentName = itemView.findViewById(R.id.rowTxtStudentName);
            txtIsActive = itemView.findViewById(R.id.rowTxtStudentIsActive);
            txtCurrentTime = itemView.findViewById(R.id.rowTxtStudentCurrentTime);
        }

//        @SuppressLint("HardwareIds")
//        void insertToSql(String studentId,String compId, ModelStudents modelStudents){
//            RequestQueue requestQueue = Volley.newRequestQueue(context);
//            requestQueue.getCache().clear();
//            String androidId = Settings.Secure.getString(context.getContentResolver(),
//                    Settings.Secure.ANDROID_ID);
//            String url = "https://www.css4dev.com/css4dev/shamApp/vote.php?studentId="+studentId+"&androidId="+androidId+"&compId="+compId;
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @SuppressLint("DefaultLocale")
//                @Override
//                public void onResponse(String response) {
//                    if (response.trim().contains("true")){
//                        SharedPreferences sp5 = context.getSharedPreferences(PREF_FILE_NAME2, Context.MODE_PRIVATE);
//                        sp5.edit().putString(modelStudents.getCompId(), "true").apply();
//                        Toast.makeText(context, "تم التصويت بنجاح", Toast.LENGTH_SHORT).show();
//                        //  modelStudents.setRating(modelStudents.getRating() + 1);
//                        txtStudent.setText(modelStudents.getTxtNameStudent());
//                    }else if (response.trim().contains("repeat")){
//                        Toast.makeText(context, "لقد قمت بالتصويت بالفعل على هذه المسابقة", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        itemView.setEnabled(true);
//                        btnRate.setEnabled(true);
//                        Toast.makeText(context, "يرجى اعادة التصويت", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    itemView.setEnabled(true);
//                    btnRate.setEnabled(true);
//                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            stringRequest.setShouldCache(false);
//            requestQueue.add(stringRequest);
//        }
    }

}