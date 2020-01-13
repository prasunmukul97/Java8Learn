package com.java.learn.CustomCollector;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TestCollector {
    public static void main(String[] args) {
        Stream<Box> boxes=Stream.of(
                new Box(2),
                new Box(3),
                new Box(4),
                new Box(14),
                new Box(10)
        );

        MinMaxCollector.MinMax<Box> boxMinMax = boxes.collect(new MinMaxCollector<>(Comparator.comparing(Box::getWeight)));

        System.out.println("Max== "+boxMinMax.getMax().get().getWeight());
        System.out.println("Min== "+boxMinMax.getMin().get().getWeight());

        Stream<String> stringVal=Stream.of("Ram","Shyam");
        List<String> collectValue = stringVal.collect(new StringListCollector<>());
        System.out.println(collectValue);

        Stream<Integer> intVal=Stream.of(1,2,3,4);
        List collect = intVal.collect(new StringListCollector<>());
        System.out.println(collect);

        String[] arr = {"java ","puzzlers ","is ","a ","good ","book"};
        String message = null;
        for(String str : arr){
            message += str;
        }
        System.out.println(message);
    }
}
