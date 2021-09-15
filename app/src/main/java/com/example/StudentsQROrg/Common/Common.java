package com.example.StudentsQROrg.Common;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.StudentsQROrg.ListFragment;
import com.example.StudentsQROrg.Login;
import com.example.StudentsQROrg.Remote.APIService;
import com.example.StudentsQROrg.Remote.RetrofitClient;
import com.example.StudentsQROrg.StatsFragment;

public class Common {
    private static final String fcmUrl ="https://fcm.googleapis.com";

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final String topicName = "News";
   // public static final String EMPLOYEE_ID = StatsFragment.employeeIdS;

    public static APIService getFCMService() {
        return RetrofitClient.getClient(fcmUrl).create(APIService.class);
    }

}