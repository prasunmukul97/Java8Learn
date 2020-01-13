package com.java.learn.OptionalExamples;

import java.util.Optional;

public class OptionalOrElseExample {
    public static void main(String[] args) {
        Optional<String> gender=Optional.of("Male");
        Optional<String> emptyGender=Optional.empty();

        System.out.println(gender.orElse("Not Available"));
        System.out.println(emptyGender.orElse("Not Available"));

        System.out.println(gender.orElseGet(()-> "Not Available"));
        System.out.println(emptyGender.orElseGet(() -> "Not Available"));

    }
}
