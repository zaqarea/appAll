//package com.example.StudentsQR.Adabter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.StudentsQR.Model.ModelPresence;
//import com.example.StudentsQR.R;
//
//import java.util.ArrayList;
//
//public class AdabterPresence extends RecyclerView.Adapter<AdabterPresence.PresenceViewHolder> {
//
//    ArrayList<ModelPresence> presencesArrayList;
//    Context context;
//
//    public AdabterPresence(ArrayList<ModelPresence> presencesArrayList, Context context) {
//        this.presencesArrayList = presencesArrayList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture, parent, false);
//        return new PresenceViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PresenceViewHolder holder, int position) {
//        ModelPresence modelPresence = presencesArrayList.get(position);
//
//        holder.txtViewPresenceTime.setText(modelPresence.getPresenceTime());
//      //  holder.txtViewstartTimeRow.setText(modelPresence.getst());
//        holder.txtViewendTimeRow.setText(modelPresence.getCurseName());
//        holder.txtViewLagRow.setText(modelPresence.getCurseName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return presencesArrayList.size();
//    }
//
//    public static class PresenceViewHolder extends RecyclerView.ViewHolder {
//        TextView txtViewPresenceTime, txtViewLectureTitle, txtViewstartTimeRow, txtViewendTimeRow, txtViewLagRow, txtViewCourseName;
//
//        public PresenceViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txtViewCourseName = itemView.findViewById(R.id.txtViewCourseName);
//            txtViewPresenceTime = itemView.findViewById(R.id.txtViewPresenceTimeRow);
//            txtViewLectureTitle = itemView.findViewById(R.id.txtViewLectureTitleRow);
//            txtViewstartTimeRow = itemView.findViewById(R.id.txtViewstartTimeRow);
//            txtViewendTimeRow = itemView.findViewById(R.id.txtViewendTimeRow);
//           // txtViewLagRow = itemView.findViewById(R.id.txtViewLagRow);
//
//        }
//
//
//    }
//
//
//}
