package com.example.reunion_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class sensor extends AppCompatActivity implements SensorEventListener {

    private TextView textView1,textView2,textView3;
    private SensorManager sensorManager;
    private Sensor gravity;
    private boolean isGravitySensorPresent;
    private AudioManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        textView1 = findViewById(R.id.X);
        textView2 = findViewById(R.id.Y);
        textView3 = findViewById(R.id.Z);

        sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null){

            gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            isGravitySensorPresent = true;
        }else {
            textView1.setText("Humidity sensor is not available");
            isGravitySensorPresent = false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null && !notificationManager.isNotificationPolicyAccessGranted()) {
                Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        textView1.setText(event.values[0]+"m/s2");
        textView2.setText(event.values[1]+"m/s2");
        textView3.setText(event.values[2]+"m/s2");

        if (event.values[2]<9.7){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            aManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null)
        {
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null){
            sensorManager.unregisterListener(this,gravity);
        }
    }
}