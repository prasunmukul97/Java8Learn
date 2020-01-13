package com.java.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample1 {
    public static void main(String[] args) {
        //To find the even number with java7 approach
        List<Integer> numbers= Arrays.asList(1,2,3,4,5);
        List<Integer> evenNumbers=new ArrayList<>();
        for (Integer number:numbers) {
            if(number%2==0){
                evenNumbers.add(number);
            }
        }
        System.out.println(evenNumbers);

        //To find the even number with java8 approach
        List<Integer> evenNums=numbers.stream().filter(num->num%2==0).collect(Collectors.toList());
        System.out.println(evenNums);

        evenNums.forEach(num-> System.out.println(num));

    }
}
