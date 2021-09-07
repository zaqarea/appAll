package com.example.StudentsQROrg.Common;


import com.example.StudentsQROrg.Remote.APIService;
import com.example.StudentsQROrg.Remote.RetrofitClient;

public class Common {
    private static final String fcmUrl ="https://fcm.googleapis.com";

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final String topicName = "News";

    public static APIService getFCMService() {
        return RetrofitClient.getClient(fcmUrl).create(APIService.class);
    }

}