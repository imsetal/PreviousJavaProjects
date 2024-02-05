package com.example.yachtclub.model;

public class User {
    public String user;
    public String password;
    public String user_group;

    public User(){}

    public User(String user, String password, String user_group) {
        this.user = user;
        this.password = password;
        this.user_group = user_group;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_group() {
        return user_group;
    }

    public void setUser_group(String user_group) {
        this.user_group = user_group;
    }
}
