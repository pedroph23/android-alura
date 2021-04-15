package com.example.schedule.ui.activy;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schedule.R;
import com.example.schedule.dao.StudentDAO;
import com.example.schedule.models.Student;

public class StudentsFormActivity extends AppCompatActivity {

    private final static String TITLE = "Create";
    private final static String TITLE_EDIT = "Edit";
    private final static String NAME_STATE_STUDENT = "student";
    private EditText name;
    private EditText phone;
    private EditText email;
    private Student studentState;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(TITLE);
        setContentView(R.layout.activity_students_form);

        this.initializer();
//        this.actionButton();
    }

    private void initializer() {
        name = findViewById(R.id.activity_students_form_name);
        phone = findViewById(R.id.activity_students_form_phone);
        email = findViewById(R.id.activity_students_form_email);

        studentState  = (Student) getIntent().getSerializableExtra("student");

        if(studentState != null) {
            boolean isEdited = true;
            setTitle(TITLE_EDIT);
            setData(studentState, name, phone, email);
        }
    }

    private void setData(Student student, EditText name, EditText phone, EditText email) {
        name.setText(student.getName());
        phone.setText(student.getNumber());
        email.setText(student.getEmail());
    }

    /**
     *
    private void actionButton() {
        Button doneBtn = findViewById(R.id.activity_students_done_btn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                save(constructor(name, phone, email));
                finish();
            }
        });
    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_students_form_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        save(constructor(name, phone, email));
        finish();
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void save(Student student) {
        StudentDAO dao = new StudentDAO();
        if(studentState != null) {
            student.setId(studentState.getId());
            dao.update(student);
        } else {
            dao.save(student);
        }
    }

    private Student constructor(EditText name, EditText email, EditText phone) {
        return new Student(name.getText().toString(), phone.getText().toString(), email.getText().toString());
    }
}