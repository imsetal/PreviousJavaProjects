package com.example.yachtclub.model;

public class Member {
    String user;
    String name;
    String gender;
    String birthday;
    int level;

    public Member(String user, String name, String gender, String birthday, int level) {
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.level = level;
    }

    public Member(){}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
