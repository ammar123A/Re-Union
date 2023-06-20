package com.example.reunion_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    private TextView nameuser;
    private EditText name;
    private Button resetButton;

    DBReunion DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        nameuser = (TextView) findViewById(R.id.resetTextView);

        name = (EditText) findViewById(R.id.resetName);

        resetButton = (Button) findViewById(R.id.resetB);

        DB = new DBReunion(this);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString();

                Boolean checkuser = DB.checkusername(user);
                if(checkuser==true){
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgotPassword.this, "User Does not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}