package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText txtEmail, txtPassword;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        txtEmail = (EditText) findViewById(R.id.userName_txt);
        txtPassword = (EditText) findViewById(R.id.password_txt);

        btnLogin = (Button) findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             String username = txtEmail.getText().toString();
             String password = txtPassword.getText().toString();

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkEmail = db.checkUsername(username);
                    if (checkEmail==true){
                        Toast.makeText(getApplicationContext(), "Email is correct", Toast.LENGTH_SHORT).show();
                        Boolean checkPassword = db.checkPassword(username, password);
                        if (checkPassword==true){
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No Account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister = (Button) findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               Intent i = new Intent(MainActivity.this, Register.class);
               startActivity(i);
           }
        });
        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                //Validation
                if (username.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUsername = db.checkUsername(username);
                    if (checkUsername == true){
                        Boolean insert = db.insert(username,password);
                        if (insert==true){
                            Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/
    }
}