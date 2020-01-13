package com.java.learn.DefaultAndStatic;

public class TestDefaultStaticMethod {

    public static void main(String[] args) {
        UserService userServ=new UserServiceImpl();
        userServ.displayRank();
        userServ.displayUser();
        UserService.displayUserName();

    }
}
