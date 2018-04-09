package com.jzerez.sockettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private static final int SERVERPORT = 42069;
    private static final String SERVER_IP = "127.0.0.1";
    private Socket socket;
    private static final String TAG = "socket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            socket = new Socket(SERVER_IP, SERVERPORT);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        String str = "bob's your uncle";
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.write(str);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
