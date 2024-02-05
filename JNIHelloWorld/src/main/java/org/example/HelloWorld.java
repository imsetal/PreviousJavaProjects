package org.example;

public class HelloWorld {
    static {
        System.load(System.getProperty("user.dir") + "/src/main/cpp/libHelloWorld.dll");
    }

    /**
     * 在C++当中输出HelloWorld
     */
    public native void printHelloWorld();

    /**
     * 通过Java传参给C++，在C++当中输出
     * @param str Java传入的字符串
     */
    public native void say(String str);

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.printHelloWorld();
        helloWorld.say("Hello World from Java!");
    }
}