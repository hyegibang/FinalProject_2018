package com.example.hbang.swithcscreen;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    public final static String HOST = "192.168.33.210";
    public final static int PORT = 6969;
    private static String TAG = "MainActivity";
    private SensorManager oriSensorManager;
    private Sensor oriSensor;
    private double xTheta;
    private double yTheta;
    private double zTheta;
    Button line;
    Button ellipse;
    Button rectangle;
    Button arc;
    Button polygon;
    Button slot;
    private String[] buttonKeys = {"a", "b", "c", "d", "e", "f","esc"};
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

        Thread recieveThread = new Thread(new MyServerThread());
        recieveThread.start();


        line = findViewById(R.id.line);
        ellipse = findViewById(R.id.ellipse);
        rectangle = findViewById(R.id.rectangle);
        arc = findViewById(R.id.arc);
        polygon = findViewById(R.id.polygon);
        slot = findViewById(R.id.slot);

        line.setOnClickListener(this);
        ellipse.setOnClickListener(this);
        rectangle.setOnClickListener(this);
        arc.setOnClickListener(this);
        polygon.setOnClickListener(this);
        slot.setOnClickListener(this);

        oriSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        oriSensor = oriSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        oriSensorManager.registerListener(this, oriSensor, 10000000);;

    }

    class MyServerThread implements Runnable {
        Socket s;
        ServerSocket ss;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        String message;
        Handler h = new Handler();

        public void run() {

            try {
                ss = new ServerSocket(6969);
                System.out.println("Running");
                while (true) {
                    s = ss.accept();
                    isr = new InputStreamReader(s.getInputStream());
                    bufferedReader = new BufferedReader(isr);
                    message = bufferedReader.readLine();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (message.equals("SLDPRT")) {
                                buttonKeys = new String[]{"l", "e", "r", "a", "p", "s","esc"};
                                line.setText("line");
                                ellipse.setText("ellipse");
                                rectangle.setText("rectangle");
                                arc.setText("arc");
                                polygon.setText("polygon");
                                slot.setText("slot");
                            }
                        }
                    });
                    h.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                // in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //String response = in.readLine();
                //System.out.print(response);
                //pw.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                String a = ((Button) V).getText().toString();
                Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
                key += buttonKeys[0];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.ellipse:
                String b = ((Button) V).getText().toString();
                Toast.makeText(this, b, Toast.LENGTH_SHORT).show();
                key += buttonKeys[1];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.rectangle:
                String c = ((Button) V).getText().toString();
                Toast.makeText(this, c, Toast.LENGTH_SHORT).show();
                key += buttonKeys[2];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.arc:
                String d = ((Button) V).getText().toString();
                Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
                key += buttonKeys[3];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.polygon:
                String e = ((Button) V).getText().toString();
                Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
                key += buttonKeys[4];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.slot:
                String f = ((Button) V).getText().toString();
                Toast.makeText(this, f, Toast.LENGTH_SHORT).show();
                key += buttonKeys[5];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.esc:
                String esc = ((Button) V).getText().toString();
                Toast.makeText(this, esc, Toast.LENGTH_SHORT).show();
                key += buttonKeys[6];
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



