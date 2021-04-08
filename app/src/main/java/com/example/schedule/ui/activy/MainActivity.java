package com.example.schedule.ui.activy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ActionProvider;

import com.example.schedule.R;
import com.example.schedule.dao.StudentDAO;
import com.example.schedule.models.Student;
import com.example.schedule.models.enums.OptionMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private StudentDAO dao = new StudentDAO();
    private ArrayAdapter<Student> adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Schedule");
        setContentView(R.layout.activity_students_list);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        listStudent();
        actionFab();
        selectRow();
    }

    private void listStudent() {
        ListView views = findViewById(R.id.activity_student_list_listview );
        adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                dao.getAll());
        views.setAdapter(adapter);

        registerForContextMenu(views);
    }

    /**
     * Create context menu with any options
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(OptionMenu.DELETE.getNameOption());
        menu.add(OptionMenu.TEST.getNameOption());

    }

    /**
     * Listen click option in context menu
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String titleOptionMenu = item.getTitle().toString();

        if(titleOptionMenu == OptionMenu.DELETE.getNameOption()) {
            this.removeRow(item);
        }

        return super.onContextItemSelected(item);
    }

    private void removeRow(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student student = adapter.getItem(menuInfo.position);
        adapter.remove(student);
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

    private void selectRow() {
       ListView listView = findViewById(R.id.activity_student_list_listview);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @SuppressLint("WrongConstant")
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              view((Student) parent.getItemAtPosition(position));
           }
       });
    }

    private void view(Student student) {
        Intent intent = new Intent(this, StudentsFormActivity.class);
        intent.putExtra("student", student);
        nextPage(intent);
    }

    private void nextPage() {
        startActivity(new Intent(this, StudentsFormActivity.class));
    }

    private void nextPage(Intent options) {
        startActivity(options);
    }

}
