package com.java.learn.InterfaceObjectMethods;

public class TestImple {
    public static void main(String[] args) {
        Interuf i= new Demo();
        System.out.println(i.toString());
        i.testMethod();
        i.display();
        System.out.println(i.getClass());
    }
}
