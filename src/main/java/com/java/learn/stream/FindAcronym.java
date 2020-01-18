package com.java.learn.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindAcronym {
    public static void main(String[] args){
        String s="Indian Space Research Organization";
        String achronym=getAcronym(s);
        System.out.println(achronym);

    }

    private static String getAcronym(String input) {
        List<String> STOP_WORD= Arrays.asList("AND","OR","BY");
        return Arrays.stream(input.toUpperCase().split("\\s"))
                .filter(word -> !STOP_WORD.contains(word))
                .map(w -> String.valueOf(w.charAt(0)))
                .collect(Collectors.joining(""));
    }
}
