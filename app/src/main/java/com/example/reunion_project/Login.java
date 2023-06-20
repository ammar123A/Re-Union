package com.example.reunion_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private TextView register, forgot;
    private EditText etuser,etPassword;
    private Button signIn, Face;

    private ImageView imgV;


    DBReunion DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgV = findViewById(R.id.imageView);

        etuser = (EditText) findViewById(R.id.username1);

        etPassword = (EditText) findViewById(R.id.password1);

        signIn = (Button) findViewById(R.id.login);

        register = (TextView) findViewById(R.id.register);

        forgot = (TextView) findViewById(R.id.forgot);

        Face = (Button) findViewById(R.id.camera);

        DB = new DBReunion(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etuser.getText().toString();
                String password = etPassword.getText().toString();

                if (user.equals("")||password.equals(""))
                    Toast.makeText(Login.this, "Please enter all fiedls", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserall= DB.checkall(user,password);
                    if(checkuserall==true){
                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainPage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if (ContextCompat.checkSelfPermission(Login.this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Login.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        Face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }
}