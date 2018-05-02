package com.example.hbang.swithcscreen;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    //public final static String HOST = "10.7.64.13";
    //public final static int PORT = 6969;

    private static String TAG = "MainActivity";

    // Orientation for rotation
    private SensorManager oriSensorManager;
    private Sensor oriSensor;
    private double xTheta;
    private double yTheta;
    private double zTheta;

    // Initialize settings for shortcut
    Button AButton;
    Button BButton;
    Button CButton;
    Button DButton;
    Button EButton;
    Button FButton;
    Button GButton;
    Button HButton;
    Button IButton;
    //Button submit;
    Button esc;

    private String[] buttonKeys = {"a", "b", "c", "d", "e", "f","g", "h", "i", "j"};
    byte bytes[];
    TextView title;
    TextView AText;
    TextView BText;
    TextView CText;
    TextView DText;
    TextView EText;
    TextView FText;
    TextView GText;
    TextView HText;
   // TextView valueM;

    Socket s;
    PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "activity started");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String HOST = intent.getStringExtra(ipconfig.IP_STRING);
        final int PORT = Integer.parseInt(intent.getStringExtra(ipconfig.PORT_NUM));
        Log.i(TAG, "ip is " + HOST);
        Log.i(TAG, "port is " + PORT);
        // Client Server
        try {
            Log.e(TAG, "socket failed");
            s = new Socket(HOST, PORT);
            pw = new PrintWriter(s.getOutputStream());
            pw.write("wifi is working");
        } catch (IOException e) {
            e.printStackTrace();

        }

        Thread recieveThread = new Thread(new MyServerThread(PORT));
        recieveThread.start();


        AButton = findViewById(R.id.AButton);
        BButton = findViewById(R.id.BButton);
        CButton = findViewById(R.id.CButton);
        DButton = findViewById(R.id.DButton);
        EButton = findViewById(R.id.EButton);
        FButton = findViewById(R.id.FButton);
        GButton = findViewById(R.id.GButton);
        HButton = findViewById(R.id.HButton);
        IButton = findViewById(R.id.IButton);

        esc = findViewById(R.id.esc);

        title = findViewById(R.id.title);
        AText = findViewById(R.id.AText);
        BText = findViewById(R.id.BText);
        CText = findViewById(R.id.CText);
        DText = findViewById(R.id.DText);
        EText = findViewById(R.id.EText);
        FText = findViewById(R.id.FText);
        GText = findViewById(R.id.GText);
        HText = findViewById(R.id.HText);

        //valueM = findViewById(R.id.valueMeasure);
        //submit = findViewById(R.id.submit);

        AButton.setOnClickListener(this);
        BButton.setOnClickListener(this);
        CButton.setOnClickListener(this);
        DButton.setOnClickListener(this);
        EButton.setOnClickListener(this);
        FButton.setOnClickListener(this);
        GButton.setOnClickListener(this);
        HButton.setOnClickListener(this);
        IButton.setOnClickListener(this);
        esc.setOnClickListener(this);

        oriSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        oriSensor = oriSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        oriSensorManager.registerListener(this, oriSensor, 10000000);;

    }

    // Server - Removed from a individual class due to getApplicationContent Error 4.24.2018
    class MyServerThread implements Runnable {
        Socket recieveSocket;
        ServerSocket ss;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        String message;
        Handler h = new Handler();
        int PORT;

        public MyServerThread(int PORT_num) {
            PORT = PORT_num;
            Log.i("server thread", "port is " + PORT);
        }
        public void run() {
            try {
                ss = new ServerSocket(PORT);
                System.out.println("Server Connected");
                while (true) {
                    recieveSocket = ss.accept();
                    isr = new InputStreamReader(recieveSocket.getInputStream());
                    bufferedReader = new BufferedReader(isr);
                    message = bufferedReader.readLine();

                    // Changes the values of string array based on the state
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (message.equals("SLDPRT")) {
                                buttonKeys = new String[]{"l", "c", "r", "z", "x", "v", "b", "e", "s"};
                                //AButton.setText("Line");
                               AButton.setBackgroundResource(R.drawable.line);
                               // BButton.setText("circle");
                              BButton.setBackgroundResource(R.drawable.circle);
                              //  CButton.setText("rect");
                               CButton.setBackgroundResource(R.drawable.rectangle);
                               DButton.setBackgroundResource(R.drawable.extrude);
                               EButton.setBackgroundResource(R.drawable.revolve);
                               FButton.setBackgroundResource(R.drawable.cut);
                               GButton.setBackgroundResource(R.drawable.filet);
                               HButton.setBackgroundResource(R.drawable.measure);
                               IButton.setBackgroundResource(R.drawable.smartd);

                                title.setText("Sketch");
                                AText.setText("LINE");
                                BText.setText("CIRCLE");
                                CText.setText("RECT");
                                DText.setText("EXTRUDE");
                                EText.setText("REVOLVE");
                                FText.setText("CUT");
                                GText.setText("FILLET");
                                HText.setText("MEASURE");


                            }

                            if (message.equals("SLDASM")) {
                                buttonKeys = new String[]{"w", "q", "i", "o", "t", "p", "o","e",""};
                                AButton.setText("Mate");
                                BButton.setText("Mass Properties");
                                CButton.setText("Interface Detection");
                                DButton.setText("Open new part");
                                EButton.setText("Exploded View");
                                FButton.setText("Plane");
                                GButton.setText("Center of Mass");
                                HButton.setText("measure");
                                IButton.setText("");
                                title.setText("Assembly");
                            }
                        }
                    });
                    h.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
            case R.id.AButton:
                String a = ((Button) V).getText().toString();
                Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
                key += buttonKeys[0];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.BButton:
                String b = ((Button) V).getText().toString();
                Toast.makeText(this, b, Toast.LENGTH_SHORT).show();
                key += buttonKeys[1];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.CButton:
                String c = ((Button) V).getText().toString();
                Toast.makeText(this, c, Toast.LENGTH_SHORT).show();
                key += buttonKeys[2];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.DButton:
                String d = ((Button) V).getText().toString();
                Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
                key += buttonKeys[3];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.EButton:
                String e = ((Button) V).getText().toString();
                Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
                key += buttonKeys[4];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.FButton:
                String f = ((Button) V).getText().toString();
                Toast.makeText(this, f, Toast.LENGTH_SHORT).show();
                key += buttonKeys[5];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.GButton:
                String g = ((Button) V).getText().toString();
                Toast.makeText(this, g, Toast.LENGTH_SHORT).show();
                key += buttonKeys[6];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.HButton:
                String h = ((Button) V).getText().toString();
                Toast.makeText(this, h, Toast.LENGTH_SHORT).show();
                key += buttonKeys[7];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.measure_input,null);
                final EditText mPassword = mView.findViewById(R.id.valueinput);
                Button mEnter = mView.findViewById(R.id.btnEnter);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( !mPassword.getText().toString().isEmpty()){
                            String inputVal = mPassword.getText().toString();
                            Toast.makeText(MainActivity.this,
                                    inputVal,
                                    Toast.LENGTH_SHORT).show();
                            pw.write(inputVal);
                            System.out.println(inputVal);
                            pw.flush();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this,
                                    R.string.error_login_msg,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;

            case R.id.IButton:
                String i = ((Button) V).getText().toString();
                Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
                key += buttonKeys[8];
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.esc:
                String esc = ((Button) V).getText().toString();
                Toast.makeText(this, esc, Toast.LENGTH_SHORT).show();
                key += "esc";
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



