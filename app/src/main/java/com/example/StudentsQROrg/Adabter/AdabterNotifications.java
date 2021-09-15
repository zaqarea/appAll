package com.example.StudentsQROrg.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.StudentsQROrg.Model.ModelNotifications;
import com.example.StudentsQROrg.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdabterNotifications extends RecyclerView.Adapter<AdabterNotifications.NotificationsViewHolder> {

    private final ArrayList<ModelNotifications> notificationsArrayList;
    private final Context context;

    public AdabterNotifications(ArrayList<ModelNotifications> notificationsArrayList, Context context) {
        this.context = context;
        this.notificationsArrayList = notificationsArrayList;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notifications, parent, false);
        return new NotificationsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        ModelNotifications modelNotifications = notificationsArrayList.get(position);

        if (modelNotifications.getContent() != null) {
            holder.txtViewRowNotificationBody.setText(modelNotifications.getContent());
        }
        if (modelNotifications.getTitle() != null) {
            holder.txtViewTitle.setText(modelNotifications.getTitle());
        }
      //  Glide.with(context).load(modelNotifications.getImage()).placeholder(R.drawable.ic_account).into(holder.imgViewRowNotification);


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
        return notificationsArrayList.size();
    }

    //Class Recycler HolderView
    static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle, txtViewRowNotificationBody;
        CircleImageView imgViewRowNotification;

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewTitle = itemView.findViewById(R.id.txtViewRowNotification);
            imgViewRowNotification = itemView.findViewById(R.id.imageProfile);
            txtViewRowNotificationBody = itemView.findViewById(R.id.txtViewRowNotificationBody);
        }
    }
}