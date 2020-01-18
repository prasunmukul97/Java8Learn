package com.java.learn.stream;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class FindPattern {
    public static void main(String[] args) {
        String pattern="ace";
        String word="Indian Space Research Organization";
        boolean value = findPattern(word, pattern);
        System.out.println(value);
    }

    private static boolean findPattern(String word, String pattern) {
        return word.contains(pattern);

    }
}
