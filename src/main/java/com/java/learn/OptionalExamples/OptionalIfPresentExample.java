package com.java.learn.OptionalExamples;

import java.util.Optional;

public class OptionalIfPresentExample {

    public static void main(String[] args) {
        Optional<String> gender=Optional.of("Male");
        Optional<String> emptyGender=Optional.empty();

        if(gender.isPresent()){
            System.out.println("Value is available.");
        }else{
            System.out.println("Value is unavailable.");
        }

        gender.ifPresent(g-> System.out.println("In gender Optional value is available :"+ g));
        //condition failed,no output print
        emptyGender.ifPresent(g-> System.out.println("Value available is :"+g));
    }
}
