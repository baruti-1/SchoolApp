package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    DatabaseHelper mySchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void registerStudent(View view){
        Intent student = new Intent(this, StudentActivity.class);
        startActivity(student);
    }

    public void registerStaff(View view){
        Intent staff = new Intent(this, StaffActivity.class);
        startActivity(staff);
    }

    public void registerCourse(View view){
        Intent course = new Intent(this, CourseActivity.class);
        startActivity(course);
    }



    public void logoutUser(View view){
        Intent logout = new Intent(this, MainActivity.class);
        startActivity(logout);
        finish();
    }
}
