package com.example.schoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginUser(View view){
        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

        //Login implementation later (Hardcoded credentials for now)
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("1234")){
            Intent login = new Intent(this, AdminActivity.class);
            startActivity(login);
            // prevent return back to login activity when back is clicked
            finish();
        }else if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter username and password",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Incorrect username or password",Toast.LENGTH_LONG).show();
        }

    }
}
