package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class StudentActivity extends AppCompatActivity {
    DatabaseHelper mySchool;
    EditText first, middle, last, email, phone, dob, username, password;
    Spinner gender;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        mySchool = new DatabaseHelper(this);

        first = (EditText)findViewById(R.id.fname);

        middle = (EditText)findViewById(R.id.mname);

        last = (EditText)findViewById(R.id.lname);

        email = (EditText)findViewById(R.id.email);

        phone = (EditText)findViewById(R.id.phone);

        gender = (Spinner)findViewById(R.id.sex);

        dob = (EditText)findViewById(R.id.dob);

        username = (EditText)findViewById(R.id.user);

        password = (EditText)findViewById(R.id.pss);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(StudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void insertStudent(View view){
        boolean studentInserted = mySchool.insertData(
                first.getText().toString(),
                middle.getText().toString(),
                last.getText().toString(),
                email.getText().toString(),
                phone.getText().toString(),
                valueOf(gender.getSelectedItem()),
                dob.getText().toString(),
                username.getText().toString(),
                password.getText().toString()
                );
                if(studentInserted)
                    Toast.makeText(this,"Student registered", Toast.LENGTH_LONG).show();
                  else
                    Toast.makeText(this,"Oops! Error", Toast.LENGTH_LONG).show();
    }

    public void showStudents(View view){
        Cursor res = mySchool.getStudents();
        if(res.getCount() == 0){
            showMessage("Sorry!", "No students yet");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Username: " + res.getString(8) + "\n");
            buffer.append("First name: " + res.getString(1) + "\n");
            buffer.append("Middle name: " + res.getString(2) + "\n");
            buffer.append("Last name: " + res.getString(3) + "\n");
            buffer.append("Gender: " + res.getString(6) + "\n");
            buffer.append("E-mail: " + res.getString(4) + "\n");
            buffer.append("Phone number: " + res.getString(5) + "\n");
            buffer.append("Date of birth: " + res.getString(7) + "\n" + "\n" + "\n");
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
