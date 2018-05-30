package com.github.bruce.model;

import java.util.List;

public class MyBean {

    private String name;
    private List<User> users;
    public MyBean() {

    }

    public MyBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
