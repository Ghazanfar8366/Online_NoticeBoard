package com.ideatech.org.online_noticeboard;

/**
 * Created by MianGhazanfar on 12/4/2016.
 */
public class Notification {
    private String id;
    private String title;
    private String message;
    private String type;
    private String time;
    private String date;

    public Notification(String id, String title, String message, String type, String time, String date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
