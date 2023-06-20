package com.example.reunion_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private ImageView banner;

    private TextView registerUser, alreadyRegister;
    private EditText etName, etEmail, etPassword, etrePassword;
    private ProgressBar progressBar;

    DBReunion DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        banner = (ImageView) findViewById(R.id.banner);

        registerUser=(Button) findViewById(R.id.confirmreg);
        alreadyRegister=(Button) findViewById(R.id.alreadyreg);

        etName = (EditText) findViewById(R.id.username);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etrePassword=(EditText) findViewById(R.id.repassword);

        DB = new DBReunion(this);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String repassword = etrePassword.getText().toString();

                if(user.equals("")||email.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(Register.this, "Please enter all fiedls", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(repassword)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false) {
                            Boolean insert = DB.insertuserdata(user,password);
                            if (insert == true) {
                                Toast.makeText(Register.this, "Register User Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Register.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}