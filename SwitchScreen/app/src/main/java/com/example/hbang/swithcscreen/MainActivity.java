/*
    Hyegi Bang, Jonathan Zerez
    Software Design | Spring 2018

   Main script of the app that functions both as a server and client. It streams the orientation data
   of the phone and outputs to the client server via wifi. When a button is pressed, it also sends
   the value a button is assigned. As a server, it receives the state of window open on computer
   and alters the screen of the phone based on the computer's state.
 */

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

    private static String TAG = "MainActivity";

    // Orientation variables for rotation
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
    Button btnEnter;
    Button esc;

    // Initializing buttonKey array
    private String[] buttonKeys = {"a", "b", "c", "d", "e", "f","g", "h", "i", "j"};
    TextView title;
    TextView AText;
    TextView BText;
    TextView CText;
    TextView DText;
    TextView EText;
    TextView FText;
    TextView GText;
    TextView HText;
    TextView IText;

    // Variables for wifi transfer
    Socket s;
    PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "activity started");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // receives wifi ip and socket user input from ipconfig.java
        btnEnter = findViewById(R.id.btnEnter);
        Intent intent = getIntent();
        final String HOST = intent.getStringExtra(ipconfig.IP_STRING);
        final int PORT = Integer.parseInt(intent.getStringExtra(ipconfig.PORT_NUM));
        Log.i(TAG, "ip is " + HOST);
        Log.i(TAG, "port is " + PORT);

        // Connects wifi socket between two devices
        try {
            Log.e(TAG, "socket failed");
            s = new Socket(HOST, PORT);
            pw = new PrintWriter(s.getOutputStream());
            pw.write("wifi is working");
        } catch (IOException e) {
            e.printStackTrace();

        }

        // Establishes client server, streams/sends data to the server.
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
        IText = findViewById(R.id.IText);

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

    class MyServerThread implements Runnable {
        /*
            Functions as a server in order to receive data from the computer. Therefore, based on the
            window state information of the computer received, the app alters its own state by only
            displaying the most relevant shortcuts are displayed. For every state, the buttonkey array
            is overwritten by the values relevant to the predetermined displayed buttons of the state.
         */

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
                    message = bufferedReader.readLine(); // receives data

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // Changes the values of string array based on the state of window
                            // along with the text and icons of the button.
                            if (message.equals("SLDPRT")) {
                                buttonKeys = new String[]{"l", "c", "r", "z", "x", "v", "b", "e", "s"};
                                AButton.setBackgroundResource(R.drawable.line);
                                BButton.setBackgroundResource(R.drawable.circle);
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
                                HText.setText("Measure");
                                IText.setText("SmartD");

                                HButton.setVisibility(View.VISIBLE);
                                IText.setVisibility(View.VISIBLE);

                                EButton.setText("");
                                FButton.setText("");
                                GButton.setText("");

                            }

                            if (message.equals("SLDASM")) {
                                buttonKeys = new String[]{"w", "q", "i", "o", "t", "p", "o","e","s"};
                                AButton.setBackgroundResource(R.drawable.mate);
                                BButton.setBackgroundResource(R.drawable.mass);
                                CButton.setBackgroundResource(R.drawable.interference);
                                DButton.setBackgroundResource(R.drawable.openpart);
                                EButton.setBackgroundResource(R.drawable.grey);
                                FButton.setBackgroundResource(R.drawable.grey);
                                GButton.setBackgroundResource(R.drawable.grey);
                                HButton.setBackgroundResource(R.drawable.measure);
                                IButton.setBackgroundResource(R.drawable.smartd);

                                EButton.setText("Exploded View");
                                FButton.setText("Plane");
                                GButton.setText("Center of Mass");

                                AText.setText("Mate");
                                BText.setText("Mass Properties");
                                CText.setText("Interference Detection");
                                DText.setText("New part");
                                EText.setText("Exploded View");
                                FText.setText("Plane");
                                GText.setText("Center of Mass");
                                HText.setText("Measure");
                                IText.setText("SmartD");
                                title.setText("Assembly");

                                // Since SmartD is not used in this state, the button is set to be invisible
                                IButton.setVisibility(View.INVISIBLE);
                                IText.setVisibility(View.INVISIBLE);
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


    // Outputs the orientation data
    @Override
    public void onSensorChanged(SensorEvent event) {
        xTheta = event.values[0];
        yTheta = event.values[1];
        zTheta = event.values[2];
        Log.i(TAG, "" + yTheta + zTheta);
        pw.write(yTheta + ", " + zTheta);
        pw.flush();
    }

    @Override
    public void onClick(View V) {
        /* Every button a user triggers, the method creates a toast and sends the value of the button.
        When smart dimension Button, IButton, is triggered, it displays a dialog that allows user to
        input a number value and sends that input data to the computer.
            *5.07.2018 sending input data currently not working - recognizes the smartd dialog on computer
            as an invalid window type
       */

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

            case R.id.IButton:
                String h = ((Button) V).getText().toString();
                Toast.makeText(this, h, Toast.LENGTH_SHORT).show();
                key += buttonKeys[7];
                System.out.println(key);
                pw.write(key);
                pw.flush();

                // Builds the dialog box for user to input values by calling measure_input.xml
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                View mView = getLayoutInflater().inflate(R.layout.measure_input,null);
                final EditText mPassword = mView.findViewById(R.id.valueinput);
                Button mEnter = mView.findViewById(R.id.btnEnter);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                // OnClick Method for submit - sends the input data to the computer
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
                            pw.write("0000");
                            pw.flush();
                        }
                    }
                });

            case R.id.HButton:
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



