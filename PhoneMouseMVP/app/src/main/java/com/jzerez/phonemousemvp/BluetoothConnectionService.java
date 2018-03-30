package com.jzerez.phonemousemvp;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

public class BluetoothConnectionService {
    private static final String TAG = "bluetooth";
    private static final String appname = "mouseMVP";
    private static final UUID insecureUUID =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
    private final BluetoothAdapter mBlueToothAdapter;
    private ConnectThread mConnectThread;
    private BluetoothDevice mmDevice;
    private UUID deviceUUID;
    Context mContext;
    ProgressDialog mProgressDialog;
    private ConnectedThread mConnectedThread;
    private AcceptThread mInsecureAcceptThread;

    public BluetoothConnectionService(BluetoothAdapter mBlueToothAdapter, Context mContext) {
        this.mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mContext = mContext;
    }
    private class AcceptThread extends Thread{
        /*
        This class runs on a different thread as to preserve the main processing power
        Sits there are waits for something to try a connection
         */
        private final BluetoothServerSocket mmServerSocket;
        public AcceptThread(){
            //create a new server socket
           BluetoothServerSocket tmp = null;
           try {
               // Sets up app name with app name and the uuid
               tmp = mBlueToothAdapter.listenUsingInsecureRfcommWithServiceRecord(appname, insecureUUID);
               Log.d(TAG, "AcceptThread: Setting up socket");
           } catch (IOException e){
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
           }
            mmServerSocket = tmp;
        }
        public void run(){
            Log.d(TAG, "run: Accept Thread Running");
            BluetoothSocket socket = null;
            //Blocking call and will only return a successful connection or exception
            try {
                //It starts to look for a connection
                Log.d(TAG, "run: RFCOM server socket start.....");
                socket = mmServerSocket.accept();
                //If it advances beyond the line above, then it has connected successfully.
                Log.d(TAG, "run: RFCOM server socket accepted connection");
            } catch (IOException e){
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
            }
            if (socket != null){
                connected(socket,mmDevice);
            }
            Log.i(TAG,"END mAcceptThread");
        }
        public void cancel() {
            Log.d(TAG, "cancel: Canceling AcceptThread.");
            try{
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "canel: Close of AcceptThread ServerSocket failed: " + e.getMessage());
            }
        }
    }
    private class ConnectThread extends Thread{
        private BluetoothSocket mmSocket;
        public  ConnectThread(BluetoothDevice device, UUID uuid){
            Log.d(TAG, "connectedThread: started.");
            mmDevice = device;
            deviceUUID = uuid;
        }
        public void run(){
            BluetoothSocket tmp = null;
            Log.i(TAG, "Run mConnectThread");
            // Get a bluetoothsocket for a connection with the given device
            try{
                Log.d(TAG, "ConnectThread: Trying to creat InsecureRfcommSocket using UUID: " +
                        insecureUUID);
                tmp = mmDevice.createInsecureRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e){
                e.printStackTrace();
                Log.e(TAG, "ConnectThread: Could not create InsecureRfcommSocket " + e.getMessage());

            }
            mmSocket = tmp;
            // Always cancel discovery after a connection, as it is memory intensive, slowing
            // things down.
            mBlueToothAdapter.cancelDiscovery();
            //This is a blocking call and will only return on a successful connection or an exception
            try {
                mmSocket.connect();
                Log.d(TAG, "run: Connectthread connected");
            } catch (IOException e) {
                e.printStackTrace();
                try{
                    mmSocket.close();
                    Log.d(TAG, "run: Closed socket");
                } catch(IOException e1) {
                    Log.e(TAG, "mConnectedThread: run: Unable to close connection in socket "
                            +e1.getMessage());
                }
                Log.d(TAG, "run: connectThread: Could not connect to UUID: " + insecureUUID);
            }
            connected(mmSocket, mmDevice);
        }
        public void cancel() {
            try {
                Log.d(TAG, "cancel: Closing Client Socket.");
                mmSocket.close();
            } catch(IOException e) {
                Log.e(TAG, "Cancel: Close() of mmSocket in ConnectThread failed "+ e.getMessage());
            }
        }
    }

    public synchronized void start() {
        Log.d(TAG, "start");
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mInsecureAcceptThread == null){
            mInsecureAcceptThread = new AcceptThread();
            mInsecureAcceptThread.start();
        }
    }

    public void startClient(BluetoothDevice device, UUID uuid) {
        Log.d(TAG, "startClient: Started.");
        mProgressDialog = ProgressDialog.show(mContext, "Connecting Bluetooth",
                "Please wait...", true);
        mConnectThread = new ConnectThread(device, uuid);
        mConnectThread.start();
    }

    private class ConnectedThread extends Thread{
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "ConnectedTread: Starting.");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            mProgressDialog.dismiss();
            try{
                tmpIn = mmSocket.getInputStream();
            } catch(IOException e) {
                e.printStackTrace();
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run(){
            //byte array object that gets the stream
            byte[] buffer = new byte[1024];
            int bytes; //bytes returned from read()
            while(true){
                try{
                    bytes = mmInStream.read(buffer);
                    String incomingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "InputStream: " + incomingMessage);
                } catch (IOException e) {
                    Log.e(TAG, "write: Error reading inputStream: " + e.getMessage());
                    break;
                }
            }
        }
        public void write(byte[] bytes){
            String text = new String(bytes, Charset.defaultCharset());
            Log.d(TAG, "write: Writing to outputstream: " + text);
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {

            }
        }

        public void cancel(){
            try{
                mmSocket.close();
            } catch (IOException e) {}
        }
    }
    private void connected(BluetoothSocket mmSocket, BluetoothDevice mmDevice){
        Log.d(TAG, "Connected: Starting.");

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(mmSocket);
        mConnectedThread.start();
    }
    public void write(byte[] out) {
        //create temp object
        ConnectedThread r;
        //Synchronize a copy of the Connected Thread
        Log.d(TAG, "write: Write Called.");
        mConnectedThread.write(out);
    }
}
