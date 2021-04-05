package com.example.schedule.dao;

import com.example.schedule.models.Student;

import java.util.ArrayList;

public class StudentDAO {

    private final static ArrayList<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }
    
    public ArrayList getAll() {
        return students;
    }
}
