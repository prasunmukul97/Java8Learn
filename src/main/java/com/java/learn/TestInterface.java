package com.java.learn;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class TestInterface {

    public static void main(String[] a){
        Utility util=new Utility();
        String name="Prasun";
        boolean isAvailable=isPresent().test(name);
        System.out.println(isAvailable);
        print().accept(name);
        System.out.println(toUpperCase().apply(name));
        print().accept(toUpperCase().apply("Saurabh"));
        System.out.println(getLength().apply("Jai Shree Ram!!"));
        printNameAndLength().accept(name,getLength().apply(name));

        Function<String, String> stringFunction = String::toUpperCase;
        print().accept(stringFunction.apply("Ram"));

        //Method Reference
        Consumer<String> print = util::print;
        print.accept("Ganesh");
        Function<String, Integer> getLength = util::getLength;
        System.out.println(getLength.apply("Ram"));

        //Test custom functional Interface
        UtilI<String> uI=System.out::println;
        uI.print("Test Functional Interface");

    }

    //Predicate
    private static Predicate<String> isPresent(){
        return name->name.contains("a");
    }

    //Consumer
    private static Consumer<String> print(){
        return name-> System.out.println(name);
    }

    //Function
    private static Function<String,String> toUpperCase(){
        return name->name.toUpperCase();
    }

    private static Function<String,Integer> getLength(){
        return name->name.length();
    }

    private static BiConsumer<String,Integer> printNameAndLength(){
        return (name,length)-> System.out.println("The name is "+name+". Its length is "+length);
    }
}


