package com.java.learn.DefaultAndStatic;

//1. Methods declared in classes win over method defined in interfaces.
//2.Otherwise, the most specific interface is selected.
//3.Otherwise, class has to call the desired implementation explicitly

interface B{
    default void display(){
        System.out.println("Inside display method of B interface");
    }
}
interface C extends A {
    default void display(){
        System.out.println("Inside display method of C interface");
    }
}
public class App implements C,B,A {
    @Override
    public void display(){
        System.out.println("Inside App class display method");
    }

    public void display2(){
        System.out.println("Inside App class display2 method");
        B.super.display();
    }
    public static void main(String[] args) {
        new App().display();
        System.out.println("--------------------------------");
        new App().display2();

        /*
        * Inside App class display method
          --------------------------------
          Inside App class display2 method
          Inside display method of B interface
        * */
    }
}
