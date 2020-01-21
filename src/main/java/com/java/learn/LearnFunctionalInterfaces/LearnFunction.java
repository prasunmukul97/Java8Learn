package com.java.learn.LearnFunctionalInterfaces;

import java.util.function.Function;

public class LearnFunction {
    public static void main(String[] args) {
        Function<String,Integer> stringToLength=String::length;
        Integer length = stringToLength.apply("Prasun");
        System.out.println(length);
    }

}
