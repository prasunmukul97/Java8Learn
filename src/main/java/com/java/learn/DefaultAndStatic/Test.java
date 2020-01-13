package com.java.learn.DefaultAndStatic;

interface A1 {
    default void doSth() {
        System.out.println("inside A1");
    }
}
interface B1 {}
interface C1 extends A1 {
    default void doSth() {
        System.out.println("inside C1");
    }
}
class Test implements C1, B1, A1 {

    public static void main(String[] args) {
        new Test().doSth();
    }
    /*inside C1
    * */
}


