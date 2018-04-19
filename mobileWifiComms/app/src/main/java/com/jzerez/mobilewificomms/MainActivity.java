package com.jzerez.mobilewificomms;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private final static String HOST = "192.168.34.203";
    private final static int PORT = 6969;
    private TextView xtextView, ytextView, ztextView;
    private SensorManager oriSensorManager;
    private Sensor oriSensor;
    private static String TAG = "MainActivity";
    private double xTheta;
    private double yTheta;
    private double zTheta;
    byte bytes [];
    String message = "Hello World!";

    // OLD METHOD VARIABLES
    InetAddress client_adress = null;
    byte[] buf = new byte[256];
    public static DatagramSocket mSocket = null;
    public static DatagramPacket mPacket = null;

    // SIMPLE METHOD CLIENT VARIABLES
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    // SIMPLE METHOD SERVER VARIABLES
    Socket recieve_socket;
    ServerSocket ss;
    InputStreamReader isr;
    BufferedReader bufferedReader;
    String recieve_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        OLD METHOD FOR SOCKETS, MORE COMPLEX. DOES NOT WORK AS OF 11:55PM, 4/17/2018

        try {
            client_adress = InetAddress.getByName(HOST);
            Log.i(TAG, client_adress + "");
        } catch (UnknownHostException e) {
            Log.e(TAG, e.getMessage());
        }

        try {
            mSocket = new DatagramSocket();
            mSocket.setReuseAddress(true);
        } catch (SocketException e) {
            mSocket = null;
            Log.e(TAG, e.getMessage());
        }
        try {
            mPacket = new DatagramPacket(buf, buf.length, client_adress, PORT);
        } catch (Exception e) {
            mSocket.close();
            mSocket = null;
            Log.e(TAG, e.getMessage());
        }
        try {
            bytes = message.getBytes("UTF-8");
            if (mPacket != null && mSocket != null) {
                Log.i(TAG, "sent???");
                mPacket.setData(bytes);
                mPacket.setLength(bytes.length);


                mSocket.send(mPacket);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //Log.e("Error", "SendBlock");
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e("Error", "SendBlock");
        }
        */

        // This code simply finds the IP address of the phone. Not necessarily needed.
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());


        // SIMPLE METHOD FOR CLIENT. WORKS AS OF 12:08AM 4/18/2018
        try {
            s = new Socket(HOST, PORT);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        // SIMPLE METHOD FOR SERVER. SEE <mobileServerThread.java> FOR DETAILS
        Thread recieveThread = new Thread(new mobileServerThread());
        recieveThread.start();




        oriSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        oriSensor = oriSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        oriSensorManager.registerListener(this, oriSensor, 50000000);

        xtextView = (TextView)findViewById(R.id.xtextView);
        ytextView = (TextView)findViewById(R.id.ytextView);
        ztextView = (TextView)findViewById(R.id.ztextView);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.i(TAG, "" + (double) ((Math.floor(event.values[0]) * 10000) / 10000));
        //Log.i(TAG, event.values[0] + "");
        xTheta = event.values[0];
        yTheta = event.values[1];
        zTheta = event.values[2];
        xtextView.setText("X: " + xTheta);
        ytextView.setText("Y: " + yTheta);
        ztextView.setText("Z: " + zTheta);
        Log.i(TAG, "" + yTheta + zTheta);
        pw.write(yTheta + ", " + zTheta);
        pw.flush();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
