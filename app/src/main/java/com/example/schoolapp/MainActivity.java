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
        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

        if(username.getText().toString().equals("admin") && password.getText().toString().equals("1234")){
            Intent login = new Intent(this, AdminActivity.class);
            startActivity(login);
        }else{
            Toast.makeText(getApplicationContext(), "Invalid username/password",Toast.LENGTH_LONG).show();
        }

    }
}
