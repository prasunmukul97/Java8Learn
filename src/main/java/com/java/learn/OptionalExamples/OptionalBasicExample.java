package com.java.learn.OptionalExamples;

/*
* Optional.ofNullable() method returns a Non-empty Optional if a value present in the given object. Otherwise returns empty Optional.
    Optionaal.empty() method is useful to create an empty Optional object.
* */

import java.util.Optional;

public class OptionalBasicExample {
    public static void main(String[] args) {
        Optional<String> gender=Optional.of("MALE");
        String answer1="Yes";
        String answer2=null;

        System.out.println("Non Empty Optional:"+gender);
        System.out.println("Non Empty Optional: Gender value : "+gender.get());
        System.out.println("Empty Optional:"+Optional.empty());

        System.out.println("ofNullable on Non Empty optional:"+Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty optional:"+Optional.ofNullable(answer2));

        //java.lang.NullPointerException
        System.out.println("of on Empty:"+Optional.of(answer2));

    }
}
