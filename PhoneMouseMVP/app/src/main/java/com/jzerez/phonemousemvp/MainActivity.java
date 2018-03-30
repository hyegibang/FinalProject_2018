package com.jzerez.phonemousemvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView xtextView, ytextView, ztextView;
    private SensorManager accSensorManager;
    private Sensor accSensor;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accSensor = accSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accSensorManager.registerListener(this, accSensor, 10000000);

        xtextView = (TextView)findViewById(R.id.xtextView);
        ytextView = (TextView)findViewById(R.id.ytextView);
        ztextView = (TextView)findViewById(R.id.ztextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.i(TAG, "" + (double) ((Math.floor(event.values[0]) * 10000) / 10000));
        //Log.i(TAG, event.values[0] + "");
        double xAcc = event.values[0];
        double yAcc = event.values[1];
        double zAcc = event.values[2];
        xtextView.setText("X: " + xAcc);
        ytextView.setText("Y: " + yAcc);
        ztextView.setText("Z: " + zAcc);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
