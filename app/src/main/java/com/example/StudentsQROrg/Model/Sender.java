package com.example.StudentsQROrg.Model;

import com.google.gson.annotations.SerializedName;

public class Sender {

    @SerializedName("to") //  "to" changed to token
    private String to;
    @SerializedName("notification")
    private Notification notification;
    @SerializedName("data")
    private DataModel data;

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public Sender() {
    }

    public Sender(String token, DataModel data) {
        to = token;
        this.data = data;
    }

    public Sender(String token, Notification notification, DataModel data) {
        this.to = token;
        this.notification = notification;
        this.data = data;
    }

    public String getToken() {
        return to;
    }

    public void setToken(String token) {
        this.to = token;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }
}
