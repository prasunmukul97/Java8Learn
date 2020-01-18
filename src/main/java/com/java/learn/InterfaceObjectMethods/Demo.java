package com.java.learn.InterfaceObjectMethods;

public class Demo implements Interuf {
    @Override
    public void testMethod() {
        System.out.println("Inside testMethod:");
    }


    public void display() {
        System.out.println("Inside display method");
        person.setFirstName("Prasun");
        System.out.println(person.getFirstName());
    }
}
