package com.java.learn.OptionalExamples;

import java.util.Optional;

public class OptionalMapFlatMapExample {
    public static void main(String[] args) {
        Optional<String> nonEmptyGender=Optional.of("male");
        Optional<String> emptyGender=Optional.empty();

        System.out.println("Non Empty Optional::"+nonEmptyGender.map(String::toUpperCase));
        System.out.println("Empty Optional::"+emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOptionalGender=Optional.of(Optional.of("male"));
        System.out.println("Non Empty Optional Gender::"+nonEmptyOptionalGender);
        //map Output: Optional.map::Optional[Optional[MALE]]
        System.out.println("Optional.map::"+nonEmptyOptionalGender.map(gender->gender.map(String::toUpperCase)));
        //flatMap Output: Optional.flatMap::Optional[MALE]
        System.out.println("Optional.flatMap::"+nonEmptyOptionalGender.flatMap(gender->gender.map(String::toUpperCase)));
    }
}
