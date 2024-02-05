package org.example;

public class Main {
    static {
        System.loadLibrary("sayC");
    }
    public native void sayC();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Main().sayC();
    }
}