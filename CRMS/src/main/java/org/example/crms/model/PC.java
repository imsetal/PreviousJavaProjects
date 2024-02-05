package org.example.crms.model;

public class PC {
    int id;
    String CPU;
    String GPU;
    String RAM;
    String StorageDevice;
    String MotherBoard;
    String state;
    public PC(int id, String CPU, String GPU, String RAM, String storageDevice, String motherBoard, String state) {
        this.id = id;
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        StorageDevice = storageDevice;
        MotherBoard = motherBoard;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getStorageDevice() {
        return StorageDevice;
    }

    public void setStorageDevice(String storageDevice) {
        StorageDevice = storageDevice;
    }

    public String getMotherBoard() {
        return MotherBoard;
    }

    public void setMotherBoard(String motherBoard) {
        MotherBoard = motherBoard;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
