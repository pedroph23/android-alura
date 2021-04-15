package com.example.schedule.models;

import java.io.Serializable;


public class Student implements Serializable {

    private String id;
    private String name;
    private String email;
    private String number;

    public Student(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

