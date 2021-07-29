package com.example.StudentsQROrg.Model;

import java.io.Serializable;

public class ModelNotifications implements Serializable {

    private String id;
    private String content;
    private String image;
    private String date;

    public ModelNotifications(String id, String content, String image, String date) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
