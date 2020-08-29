package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText;
    private TextView xText2, yText2, zText2;
    private TextView xText3, yText3, zText3;
    private TextView Text4, Text5, Text6, Text7;
    private Sensor mySensor;
    private Sensor mySensor2;
    private Sensor mySensor3;
    private SensorManager sm;
    private Button nextButton;
    float SHAKE_THRESHOLD = 50.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensor2 = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mySensor3 = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        xText2 = (TextView)findViewById(R.id.xText2);
        yText2 = (TextView)findViewById(R.id.yText2);
        zText2 = (TextView)findViewById(R.id.zText2);

        xText3 = (TextView)findViewById(R.id.xText3);
        yText3 = (TextView)findViewById(R.id.yText3);
        zText3 = (TextView)findViewById(R.id.zText3);

        Text4 = (TextView)findViewById(R.id.Text4);
        Text5 = (TextView)findViewById(R.id.Text5);
        Text6 = (TextView)findViewById(R.id.Text6);
        Text7 = (TextView)findViewById(R.id.Text7);

        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    float x,y,z,last_x=0f,last_y=0f,last_z=0f;
    long lastUpdate = 0, shakeTime = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                xText.setText("X: " + event.values[0]);
                yText.setText("Y: " + event.values[1]);
                zText.setText("Z: " + event.values[2]);

                // obrót
                if(event.values[0] > 7.0f )
                    Text4.setText("Lewy bok");
                else if(event.values[0] < -7.0f)
                    Text4.setText("Prawy bok");
                else Text4.setText("-");

                if(event.values[1] > 7.0f )
                    Text5.setText("Dolny bok");
                else if(event.values[1] < -7.0f)
                    Text5.setText("Górny bok");
                else Text5.setText("-");

                if(event.values[2] > 7.0f )
                    Text6.setText("Ekran w górę");
                else if(event.values[2] < -7.0f)
                    Text6.setText("Ekran w dół");
                else Text6.setText("-");

                //potrząsanie

                long curTime = System.currentTimeMillis();
                // only allow one update every 100ms.
                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];

                    float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        Text7.setText("POTRZĄSANIE");
                        shakeTime = curTime;
                        //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    }
                    else if (curTime - shakeTime > 5000)
                        Text7.setText("-");

                    last_x = x;
                    last_y = y;
                    last_z = z;
                }

                break;
            case Sensor.TYPE_GRAVITY:
                xText2.setText("X: " + event.values[0]);
                yText2.setText("Y: " + event.values[1]);
                zText2.setText("Z: " + event.values[2]);

                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                xText3.setText("X: " + event.values[0]);
                yText3.setText("Y: " + event.values[1]);
                zText3.setText("Z: " + event.values[2]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,mySensor2,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,mySensor3,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
