package com.example.yachtclub.model;

public class Review {
    int id;
    String user;
    int coob;
    String time;
    String content;

    public Review(int id, String user, int coob, String time, String content) {
        this.id = id;
        this.user = user;
        this.coob = coob;
        this.time = time;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCoob() {
        return coob;
    }

    public void setCoob(int coob) {
        this.coob = coob;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
