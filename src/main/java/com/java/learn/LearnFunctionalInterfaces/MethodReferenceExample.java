package com.java.learn.LearnFunctionalInterfaces;

import java.util.Arrays;

@FunctionalInterface
interface MyInterface1{
    public abstract void displayUserName(String name);
}

@FunctionalInterface
interface MyInterface2{
    public abstract void print();
}

@FunctionalInterface
interface MyInterface{
    void display(String name);
}

@FunctionalInterface
interface MyInterfaceNoParametr{
    void display();
}

class Hello{
    public Hello(String name){
        System.out.println("The name is:"+name);
    }
    public Hello(){
        System.out.println("Inside Empty Constructor");
    }

    public void printValue(){
        System.out.println("Inside print value method");
    }
}

class MyClass{
    public void printDemo(){
        System.out.println("Hi, This method is called using method reference");
    }
    public static void printUserName(String userName){
        System.out.println("Inside MyClass->The user name is :"+userName);
    }
}


public class MethodReferenceExample {
    public static void main(String[] args) {
        MyClass myClass=new MyClass();

        //Example1:1. Method reference to an instance method of an object – object::instanceMethod
            MyInterface2 testInterface = () -> System.out.println("Hi, This method is called using lambda expression");
            testInterface.print();
            //Alternate way using method reference:
            MyInterface2 myInterface=myClass::printDemo;
            myInterface.print();

        //Example2:2. Method reference to a static method of a class – Class::staticMethod
            MyInterface1 testInterface1=(name)-> System.out.println("The name is:"+name);
            testInterface1.displayUserName("Prasun");
            //Alternate way using method reference
            MyInterface1 myInterface1=MyClass::printUserName;
            myInterface1.displayUserName("Saurabh");

        //Example3: Method reference to an instance method of an arbitrary object of a particular type – Class::instanceMethod
            String[] nameArray={"Ram","Shyam","Mohan","Hanuman","Sita","Shiva"};
            //sort an array in assending order.
            Arrays.sort(nameArray,String::compareTo);
            Arrays.stream(nameArray).map(String::toUpperCase).forEach(System.out::println);

        //Example4: 4. Method reference to a constructor – Class::new
            MyInterface ref=Hello::new; // corresponding parametarized hello class constructor will be called when method is invoked.
            ref.display("Ram");  // 1 parameter constructor will be called.

            MyInterfaceNoParametr ref2=Hello::new;
            ref2.display(); //No parameter constructor will be called
    }
}
