package com.example.yachtclub.model;

public class Yacht {
    int id;
    String name;
    String color;
    String state;
    int daily_price;

    public Yacht(int id, String name, String color,String state,int daily_price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.state=state;
        this.daily_price=daily_price;
    }

    public Yacht(String name, String color,String state,int daily_price) {
        this.name = name;
        this.color = color;
        this.state=state;
        this.daily_price=daily_price;
    }

    public int getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(int daily_price) {
        this.daily_price = daily_price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Yacht(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Yacht(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
