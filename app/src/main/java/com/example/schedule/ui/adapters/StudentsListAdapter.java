package com.example.schedule.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.schedule.R;
import com.example.schedule.models.Student;
import com.example.schedule.ui.activy.MainActivity;

import java.util.ArrayList;

public class StudentsListAdapter extends BaseAdapter {

    private ArrayList<Student> students = new ArrayList<>();
    private Context context;

    public StudentsListAdapter(Context context, ArrayList students) {
        this.context = context;
        this.students = students;
    }


    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View createdView = createView(parent);

        createdView = populateTextView(createdView, position);

        return createdView;
    }

    private View createView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(
                        R.layout.item_students,
                        parent,
                        false
                );
    }

    public void remove(Object object) {
        this.students.remove(object);
        notifyDataSetChanged(); // Identify changes in data base => Best Practical
    }

    private View populateTextView(View createdView, int position) {
        Student student = students.get(position);

        TextView name = createdView.findViewById(R.id.activity_students_item_name);
        TextView phone = createdView.findViewById(R.id.activity_students_item_phone);

        name.setText(student.getName());
        phone.setText(student.getNumber());

        return createdView;
    }


}
