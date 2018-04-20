package com.example.hbang.swithcscreen;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.StrictMode;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



public class MainActivity extends AppCompatActivity implements View.OnClickListener , SensorEventListener {
    public final static String HOST = "192.168.33.210";
    public final static int PORT = 6969;
    private static String TAG = "MainActivity";
    private SensorManager oriSensorManager;
    private Sensor oriSensor;
    private double xTheta;
    private double yTheta;
    private double zTheta;
    byte bytes[];

    Socket s;
    PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            s = new Socket(HOST, PORT);
            pw = new PrintWriter(s.getOutputStream());
            //pw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread recieveThread = new Thread(new mobileServerThread());
        recieveThread.start();


        Button line = findViewById(R.id.line);
        Button ellipse = findViewById(R.id.ellipse);
        Button rectangle = findViewById(R.id.rectangle);
        Button arc = findViewById(R.id.arc);
        Button polygon = findViewById(R.id.polygon);
        Button slot = findViewById(R.id.slot);

        line.setOnClickListener(this);
        ellipse.setOnClickListener(this);
        rectangle.setOnClickListener(this);
        arc.setOnClickListener(this);
        polygon.setOnClickListener(this);
        slot.setOnClickListener(this);

        oriSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        oriSensor = oriSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        oriSensorManager.registerListener(this, oriSensor, 10000000);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.i(TAG, "" + (double) ((Math.floor(event.values[0]) * 10000) / 10000));
        //Log.i(TAG, event.values[0] + "");
        xTheta = event.values[0];
        yTheta = event.values[1];
        zTheta = event.values[2];
        Log.i(TAG, "" + yTheta + zTheta);
        pw.write(yTheta + ", " + zTheta);
        pw.flush();
    }

    @Override
    public void onClick(View V) {
        String key = "button: ";
        switch (V.getId()) {
            case R.id.line:
                String line = ((Button)V).getText().toString();
                Toast.makeText(this, line, Toast.LENGTH_SHORT).show();
                key += "l";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.ellipse:
                String ellipse = ((Button)V).getText().toString();
                Toast.makeText(this, ellipse, Toast.LENGTH_SHORT).show();
                key += "e";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.rectangle:
                String rectangle = ((Button)V).getText().toString();
                Toast.makeText(this, rectangle, Toast.LENGTH_SHORT).show();
                key += "r";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.arc:
                String arc = ((Button)V).getText().toString();
                Toast.makeText(this, arc, Toast.LENGTH_SHORT).show();
                key += "a";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.polygon:
                String polygon = ((Button)V).getText().toString();
                Toast.makeText(this, polygon, Toast.LENGTH_SHORT).show();
                key += "p";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.slot:
                String slot = ((Button)V).getText().toString();
                Toast.makeText(this, slot, Toast.LENGTH_SHORT).show();
                key += "s";
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}



