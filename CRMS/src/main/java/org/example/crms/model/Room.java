package org.example.crms.model;

public class Room {
    String room;
    int seat_num;

    public Room(String room, int seat_num) {
        this.room = room;
        this.seat_num = seat_num;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }
}
