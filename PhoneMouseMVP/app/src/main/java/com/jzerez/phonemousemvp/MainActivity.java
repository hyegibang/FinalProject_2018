package com.jzerez.phonemousemvp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.ByteBuffer;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView xtextView, ytextView, ztextView;
    private SensorManager accSensorManager;
    private Sensor accSensor;
    private static String TAG = "MainActivity";
    BluetoothAdapter mBluetoohAdapter;
    BluetoothConnectionService mBluetoothConnection;
    private double xAcc;
    private double yAcc;
    private double zAcc;

    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mBluetoohAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoohAdapter.ERROR);
                switch(state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "onReceive: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "onReceive: STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "onReceive: STATE TURNING ON");
                        break;

                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accSensor = accSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accSensorManager.registerListener(this, accSensor, 100000000);

        xtextView = (TextView)findViewById(R.id.xtextView);
        ytextView = (TextView)findViewById(R.id.ytextView);
        ztextView = (TextView)findViewById(R.id.ztextView);
        Button btnBluetooth = (Button)findViewById(R.id.bluetoothButton);
        mBluetoohAdapter = BluetoothAdapter.getDefaultAdapter();


        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick: button pressed");
                enableDisableBT();
            }
        });

        Button btnPoke = (Button)findViewById(R.id.poke);
        btnPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] bytes = toByteArray(xAcc);
                mBluetoothConnection.write(bytes);
            }
        });

    }


    public void startBTConnection(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startBTConnection: Initializing RFCOM Bluetooth connection");
        mBluetoothConnection.startClient(device, uuid);
    }

    public void enableDisableBT(){
        if (mBluetoohAdapter == null){
            Log.d(TAG, "enableDisabled: does not have BT capabilities");
        }
        if (!mBluetoohAdapter.isEnabled()){
            Log.d(TAG, "enableDisableBT; enabling BT");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntent);
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mReceiver, BTIntent);
        }
        if (mBluetoohAdapter.isEnabled()){
            Log.d(TAG, "enableDisableBT; disabling BT");
            mBluetoohAdapter.disable();
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mReceiver , BTIntent);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.i(TAG, "" + (double) ((Math.floor(event.values[0]) * 10000) / 10000));
        //Log.i(TAG, event.values[0] + "");
        xAcc = event.values[0];
        yAcc = event.values[1];
        zAcc = event.values[2];
        xtextView.setText("X: " + xAcc);
        ytextView.setText("Y: " + yAcc);
        ztextView.setText("Z: " + zAcc);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
