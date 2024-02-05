package com.example.yachtclub.model;

public class Consumption {
    int order_num;
    String user;
    int id;
    String type;
    String time;
    float sum;
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Consumption(int order_num, String user, int id, String type, String time, float sum, String state) {
        this.order_num = order_num;
        this.user = user;
        this.id = id;
        this.type=type;
        this.time = time;
        this.sum = sum;
        this.state=state;
    }

    public Consumption(int order_num, String user, int id, String type, float sum, String state) {
        this.order_num = order_num;
        this.user = user;
        this.id = id;
        this.type=type;
        this.sum = sum;
        this.state=state;
    }

    public Consumption(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
