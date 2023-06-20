package com.example.reunion_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GuestList extends AppCompatActivity {

    EditText ename, eplate, ereport;
    Button add, edit, delete, view,buts;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        buts=findViewById(R.id.btnsensor);
        buts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSens();
            }
        });

        ename = findViewById(R.id.ename);
        eplate = findViewById(R.id.eplate);
        ereport = findViewById(R.id.ereport);

        add = findViewById(R.id.btnadd);
        edit = findViewById(R.id.btnedit);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnview);

        DB = new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                String plate = eplate.getText().toString();
                String report = ereport.getText().toString();
                
                Boolean addata = DB.addguestdata(name,plate,report);
                if (addata==true)
                    Toast.makeText(GuestList.this, "NEW GUEST REPORT", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GuestList.this, "REPORT NOT INSERTED", Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                String plate = eplate.getText().toString();
                String report = ereport.getText().toString();

                Boolean editdata = DB.editguestdata(name,plate,report);
                if (editdata==true)
                    Toast.makeText(GuestList.this, "REPORT UPDATED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GuestList.this, "REPORT NOT UPDATED", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ename.getText().toString();
                Boolean deletedata = DB.deleteguestdata(name);
                if (deletedata==true)
                    Toast.makeText(GuestList.this, "REPORT DELETED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GuestList.this, "REPORT NOT DELETED", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(GuestList.this, "NO GUEST REPORT", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Plate Number :"+res.getString(1)+"\n");
                    buffer.append("Report :"+res.getString(2)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(GuestList.this);
                builder.setTitle("GuestList");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
    public void openSens(){
        Intent intent = new Intent(this, sensor.class);
        startActivity(intent);
    }
}