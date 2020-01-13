package com.java.learn.OptionalExamples;

import java.util.Optional;

public class OptionalFilterExample {

    public static void main(String[] args) {
        Optional<String> gender =Optional.of("MALE");
        Optional<String> emptyGender=Optional.empty();
        //Filter on Optional
        System.out.println(gender.filter(g->g.equals("male")));
        System.out.println(gender.filter(g->g.equals("MALE")));
        System.out.println(gender.filter(g->g.equals("MALE")).map(String::toLowerCase));
        System.out.println(gender.filter(g->g.equalsIgnoreCase("MALE")));
        System.out.println(emptyGender.filter(g->g.equalsIgnoreCase("MALE")));
    }
}
