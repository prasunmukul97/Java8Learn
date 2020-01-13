package com.java.learn.DefaultAndStatic;

//1. Methods declared in classes win over method defined in interfaces.
public interface A {
    default void display(){
        System.out.println("Inside A's interface display method");
    }
}
