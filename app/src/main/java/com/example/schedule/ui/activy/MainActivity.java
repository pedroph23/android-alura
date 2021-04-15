package com.example.schedule.ui.activy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schedule.R;
import com.example.schedule.dao.StudentDAO;
import com.example.schedule.models.Student;
import com.example.schedule.models.enums.OptionMenu;
import com.example.schedule.ui.adapters.StudentsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private StudentDAO dao = new StudentDAO();
    private StudentsListAdapter adapter;

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

        listStudents();
        actionFab();
        selectRow();

    }

    private void listStudents() {
        ListView views = findViewById(R.id.activity_student_list_listview);
        this.configureAdapter(views);
        registerForContextMenu(views);
    }


    private void configureAdapter(ListView views) {
        adapter = new StudentsListAdapter(this, dao.getAll());
        views.setAdapter(adapter);
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
            openAlertDialog(item);
        }

        return super.onContextItemSelected(item);
    }

    private void openAlertDialog(@NonNull MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Remove Student")
                .setMessage("Do you want to remove student ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    this.removeRow(item);
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void removeRow(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student student = (Student) adapter.getItem(menuInfo.position);
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
