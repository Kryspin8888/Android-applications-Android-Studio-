package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {
    private TextView textViewX, textViewY, textViewZ;
    private Sensor mySensor;
    private SensorManager sm;
    Spinner dropdown;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dropdown = (Spinner)findViewById(R.id.spinner1);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        textViewX = (TextView)findViewById(R.id.textViewX);
        textViewY = (TextView)findViewById(R.id.textViewY);
        textViewZ = (TextView)findViewById(R.id.textViewZ);

        List<Sensor> sensorsList = sm.getSensorList(Sensor.TYPE_ALL);

        String[] items = new String[sensorsList.size()];
        for (Sensor s : sensorsList) {
            items[i] = s.getName();
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == i) {

                textViewX.setText("X: " + event.values[0]);
                textViewY.setText("Y: " + event.values[1]);
                textViewZ.setText("Z: " + event.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
