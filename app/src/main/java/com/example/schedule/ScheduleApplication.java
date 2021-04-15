package com.example.schedule;

import android.app.Application;

import com.example.schedule.dao.StudentDAO;
import com.example.schedule.models.Student;

import java.util.ArrayList;
import java.util.List;

public class ScheduleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createStudent();
    }

    private void createStudent() {
        Student student = new Student("Teste", "Teste", "Teste");
        StudentDAO dao = new StudentDAO();
        dao.save(student);
    }
}
