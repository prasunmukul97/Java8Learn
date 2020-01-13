package com.java.learn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestClass1 {

    public static void main(String[] a){
        List<String> nameList= Arrays.asList("Ram","Shyam","Mohan","Hanuman","Ganesh");
        for(String name:nameList){
            if(isStartWithS().test(name)){
                System.out.println(name);
            }
        }
    }

    private static Predicate<String> isStartWithS() {
      return (name)->name.startsWith("S");

    }
}
