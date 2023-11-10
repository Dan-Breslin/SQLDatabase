package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name, password, confirm;
    Button registerbtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        name = (EditText)  findViewById(R.id.eText_Email);
        password = (EditText) findViewById(R.id.eText_Pass);
        confirm = (EditText) findViewById(R.id.eText_PassConfirm);

        registerbtn = (Button)  findViewById(R.id.btn_NewUser);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s = name.getText().toString();
                String password_s = password.getText().toString();
                String confirm_s = confirm.getText().toString();

                if (name_s.equals("")||password_s.equals("")||confirm_s.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }else {
                    if (password_s.equals(confirm_s)){
                        Boolean checkEmail = db.checkUsername(name_s);
                        if (checkEmail == false){
                            Boolean insert = db.insert(name_s,password_s);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "UPasswords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}