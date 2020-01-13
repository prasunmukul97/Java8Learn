package com.java.learn.TaskDetails;

import java.util.Optional;
/*
    Optional.empty()-This is used to create an Optional when value is not present.
    Optional.of(value)-This is used to create an Optional from a non-null value.It throws NullPointerException
    when value is null.
* */
public class User {
    private final String username;
    private Optional<String> fullname;

    public User(String username){
        this.username=username;
        this.fullname=Optional.empty();
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = Optional.of(fullname);
    }
}
