package com.example.hbang.swithcscreen;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import com.example.hbang.swithcscreen.MainActivity;

public class sketchin extends AppCompatActivity implements View.OnClickListener{
    Socket s;
    PrintWriter pw;
    String message = "Hello World!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketchin);

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

        try {
            s = new Socket(MainActivity.HOST, MainActivity.PORT);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Thread recieveThread = new Thread(new mobileServerThread());
        recieveThread.start();

    }

    @Override
    public void onClick(View V) {
        int key = 0;
        switch (V.getId()) {
            case R.id.line:
                String sketch = ((Button)V).getText().toString();
                Toast.makeText(this, sketch, Toast.LENGTH_SHORT).show();
                key = 5;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.ellipse:
                String ellipse = ((Button)V).getText().toString();
                Toast.makeText(this, ellipse, Toast.LENGTH_SHORT).show();
                key = 6;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case R.id.rectangle:
                String rectangle = ((Button)V).getText().toString();
                Toast.makeText(this, rectangle, Toast.LENGTH_SHORT).show();
                key = 7;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.arc:
                String arc = ((Button)V).getText().toString();
                Toast.makeText(this, arc, Toast.LENGTH_SHORT).show();
                key = 8;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.polygon:
                String polygon = ((Button)V).getText().toString();
                Toast.makeText(this, polygon, Toast.LENGTH_SHORT).show();
                key = 9;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;

            case  R.id.slot:
                String slot = ((Button)V).getText().toString();
                Toast.makeText(this, slot, Toast.LENGTH_SHORT).show();
                key = 10;
                System.out.println(key);
                pw.write(key);
                pw.flush();
                break;
        }
    }

}

