package com.example.hbang.swithcscreen;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class mobileServerThread implements  Runnable{
    Socket recieveSocket;
    ServerSocket ss;
    InputStreamReader isr;
    BufferedReader bufferedReader;
    String recieve_message;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(6969);
            while (true) {
                recieveSocket = ss.accept();
                isr = new InputStreamReader(recieveSocket.getInputStream());
                bufferedReader = new BufferedReader(isr);
                recieve_message = bufferedReader.readLine();
                Log.i("RECIEVED", recieve_message);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

