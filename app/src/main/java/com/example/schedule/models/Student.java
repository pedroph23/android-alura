package com.example.schedule.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Student() { }

    @Override
    public String toString() {
        return name;
    }
}
