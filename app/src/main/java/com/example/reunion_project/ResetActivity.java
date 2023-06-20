package com.example.reunion_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    private TextView username;
    private EditText pass, repass;
    private Button confirm;

    DBReunion DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = (TextView) findViewById(R.id.resetText);

        pass = (EditText) findViewById(R.id.resetpass);
        repass = (EditText) findViewById(R.id.resetpasscon);

        confirm = (Button) findViewById(R.id.confirmreset);

        DB = new DBReunion(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

                if (password.equals(repassword)){

                    Boolean checkpassword = DB.updatepassword(user, password);
                    if (checkpassword == true){
                        Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ResetActivity.this, "Password Updated Fail", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ResetActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}