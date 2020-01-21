package com.java.learn.LearnFunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LearnConsumer {
    public static void main(String[] args) {
        List<String> nameList= Arrays.asList("Bob","Mario","Ram","Alex");
        Consumer<List<String>> printName=list -> {
            for (String name:list ) {
                System.out.println(name.toUpperCase());
            }
        };

        printName.accept(nameList);
    }

}
