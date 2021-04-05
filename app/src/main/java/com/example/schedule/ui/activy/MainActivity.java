package com.example.schedule.ui.activy;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.schedule.R;
import com.example.schedule.dao.StudentDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends Activity {

    private StudentDAO dao = new StudentDAO();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listStudent();
        actionFab();

    }

    private void listStudent() {
        ListView views = findViewById(R.id.activity_student_list_listview );
        views.setAdapter(new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                dao.getAll()
        ));
    }


    private void actionFab() {
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });
    }

    private void nextPage() {
        startActivity(new Intent(this, StudentsFormActivity.class));
    }

}
