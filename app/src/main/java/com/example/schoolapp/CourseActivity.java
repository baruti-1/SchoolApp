package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class CourseActivity extends AppCompatActivity {
    DatabaseHelper mySchool;
    EditText code, name, credits, semester, year;
    Spinner option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mySchool = new DatabaseHelper(this);
        code = (EditText)findViewById(R.id.ccd);
        name = (EditText)findViewById(R.id.cnm);
        credits = (EditText)findViewById(R.id.crdts);
        semester = (EditText)findViewById(R.id.smstr);
        year = (EditText)findViewById(R.id.yos);
        option = (Spinner)findViewById(R.id.cours);

    }

    public void addDetails(View view){
        boolean courseInserted = mySchool.insertCourse(
                code.getText().toString(),
                name.getText().toString(),
                credits.getText().toString(),
                semester.getText().toString(),
                year.getText().toString(),
                valueOf(option.getSelectedItem())
        );
        if(courseInserted)
            Toast.makeText(this,"Course registered", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Oops! Error", Toast.LENGTH_LONG).show();
    }

    public void showCourses(View view){
        Cursor res = mySchool.getCourses();
        if(res.getCount() == 0){
            showMessage("Sorry!", "No courses yet");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Course code: " + res.getString(0) + "\n");
            buffer.append("Course name: " + res.getString(1) + "\n");
            buffer.append("Credits: " + res.getString(2) + "\n");
            buffer.append("Semester: " + res.getString(3) + "\n");
            buffer.append("Year: " + res.getString(4) + "\n");
            buffer.append("Category: " + res.getString(5) + "\n" +"\n" + "\n");
        }
        showMessage("Students", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

