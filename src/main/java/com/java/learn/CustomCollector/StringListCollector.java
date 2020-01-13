package com.java.learn.CustomCollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringListCollector<T> implements Collector<T, List, List<T>> {
    @Override
    public Supplier<List> supplier() {
        return (Supplier<List>)ArrayList::new;
    }

    @Override
    public BiConsumer<List, T> accumulator() {
        return (list,e)->list.add(e);
    }

    @Override
    public BinaryOperator<List> combiner() {
        return (l1,l2)->{
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Function<List, List<T>> finisher() {
        return castingIdentity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public <I,R> Function<I,R> castingIdentity(){
        return i -> (R)i;
    }
}
