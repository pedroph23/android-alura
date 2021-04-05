package com.example.schedule.ui.activy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schedule.R;
import com.example.schedule.dao.StudentDAO;
import com.example.schedule.models.Student;

public class StudentsFormActivity extends AppCompatActivity {

    private final static String TITLE = "Student Form";
    private EditText name;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(TITLE);
        setContentView(R.layout.activity_students_form);

        this.initializer();
        this.actionButton();

    }

    private void initializer() {
        name = findViewById(R.id.activity_students_form_name);
        phone = findViewById(R.id.activity_students_form_phone);
        email = findViewById(R.id.activity_students_form_email);
    }

    private void actionButton() {
        Button doneBtn = findViewById(R.id.activity_students_done_btn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(constructor(name, phone, email));
                finish();
            }
        });
    }

    private void save(Student student) {
        StudentDAO dao = new StudentDAO();
        dao.save(student);
    }

    private Student constructor(EditText name, EditText email, EditText phone) {
        return new Student(name.getText().toString(), email.getText().toString(), phone.getText().toString());
    }
}