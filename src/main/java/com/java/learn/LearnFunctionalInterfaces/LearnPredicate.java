package com.java.learn.LearnFunctionalInterfaces;

import java.util.function.Predicate;

public class LearnPredicate {
    public static void main(String[] args) {
        Predicate<String> hasLengthGreaterThan10= s -> s.length()>10;
        Predicate<String> hasA=(String s)->s.contains("a");
        Predicate<String> hasLengthLessThan20=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()<20;
            }
        };

        boolean testVal = hasLengthLessThan20.test("ps");
        System.out.println(testVal);

        boolean resultVal=new TestA().test("Prasun");
        System.out.println(resultVal);

        boolean result=hasA.test("saurabh");
        System.out.println(result);

        boolean result1 = hasLengthGreaterThan10.and(hasLengthLessThan20).test("Prasun Saurabh");
        System.out.println(result1);

        boolean result3 = hasLengthGreaterThan10.negate().test("Saurabh");
        System.out.println(result3);

        boolean result4 = hasLengthGreaterThan10.or(hasLengthLessThan20).test("Prasun Saurabh123456789012345678901234567890");
        System.out.println(result4);

        pred("Prasun",s->s.length()>5);
        pred(null,isNotNullAndEmpty()); //OutPut: Name not found
        pred("Saurabh",isNotNullAndEmpty());//Name is Saurabh
    }

    static void pred(String name, Predicate<String> predicate)
    {
        if (predicate.test(name)) {
            System.out.println("Name is: " + name);
        }else{
            System.out.println("Name not found");
        }
    }

    private static Predicate<String> isNotNullAndEmpty(){
       return s ->s!=null && !s.isEmpty() ;
    }
}

class TestA implements Predicate<String>{
    @Override
    public boolean test(String s) {
        return s.contains("a");
    }
}
