package org.example;

public class Main {
    static {
        System.loadLibrary("Cadd");
    }

    public native int add(int a,int b);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(new Main().add(1,2));
    }
}