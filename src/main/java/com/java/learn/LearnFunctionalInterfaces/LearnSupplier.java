package com.java.learn.LearnFunctionalInterfaces;

import java.util.function.Supplier;

public class LearnSupplier {
    public static void main(String[] args) {
        Supplier<Double> random_Value=()->Math.random();
        for(int i=0;i<5;i++)
        System.out.println(random_Value.get());
    }
}
