package com.java.learn.DefaultAndStatic;

public interface UserService {
    default void displayUser(){
        System.out.println("Inside displayUser default method in User service interface");
    }

    static void displayUserName(){
        System.out.println("Inside displayUserName static method in user service interface");
    }

    void displayRank();
}
