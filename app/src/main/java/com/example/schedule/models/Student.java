package com.example.schedule.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String name;
    private String email;
    private String number;

    public Student(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    @Override
    public String toString() {
        return name;
    }
}
