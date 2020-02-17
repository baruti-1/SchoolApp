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

public class StaffActivity extends AppCompatActivity {
    DatabaseHelper mySchool;
    EditText first1, middle1, last1, email1, phone1, dob1, username1, password1;
    Spinner gender1;
    DatePickerDialog picker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        mySchool = new DatabaseHelper(this);

        first1 = (EditText)findViewById(R.id.fname1);

        middle1 = (EditText)findViewById(R.id.mname1);

        last1 = (EditText)findViewById(R.id.lname1);

        email1 = (EditText)findViewById(R.id.email1);

        phone1 = (EditText)findViewById(R.id.phone1);

        gender1 = (Spinner)findViewById(R.id.sex1);

        dob1 = (EditText)findViewById(R.id.dob1);

        username1 = (EditText)findViewById(R.id.user1);

        password1 = (EditText)findViewById(R.id.pss1);

        dob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker1 = new DatePickerDialog(StaffActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker1.show();
            }
        });
    }

    public void insertStaffs(View view){
        boolean studentInserted = mySchool.insertStaff(
                first1.getText().toString(),
                middle1.getText().toString(),
                last1.getText().toString(),
                email1.getText().toString(),
                phone1.getText().toString(),
                valueOf(gender1.getSelectedItem()),
                dob1.getText().toString(),
                username1.getText().toString(),
                password1.getText().toString()
        );
        if(studentInserted)
            Toast.makeText(this,"Staff registered", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Oops! Error", Toast.LENGTH_LONG).show();
    }

    public void showStaffs(View view){
        Cursor res = mySchool.getStaffs();
        if(res.getCount() == 0){
            showMessage("Sorry!", "No staffs yet");
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
        showMessage("Staffs", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
