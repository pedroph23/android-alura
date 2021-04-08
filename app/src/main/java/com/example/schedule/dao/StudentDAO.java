package com.example.schedule.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.schedule.models.Student;

import java.util.ArrayList;
import java.util.UUID;

public class StudentDAO {


    private final static ArrayList<Student> students = new ArrayList<>();

    public void save(Student student) {
        student.setId(UUID.randomUUID().toString());
        students.add(student);
    }

    public ArrayList getAll() {
        return students;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void remove(String id) {
        students.removeIf(s -> s.getId() == id);
    }

    public Student findByPosition(int position) {
        return students.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update(Student student) {
        for (int i = 0; i <= students.size(); i++) {
            if(students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                break;
            }
        }
    }
}
