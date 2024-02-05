package org.example.crms.model;

public class Management {
    private int pcId;
    private String room;

    public Management(int pcId, String room) {
        this.pcId = pcId;
        this.room = room;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
