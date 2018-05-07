package com.example.hbang.swithcscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

    // Allows users to input IP and host number.

public class ipconfig extends AppCompatActivity implements View.OnClickListener{
    EditText IP_config_Edittext;
    EditText port_config_Edittext;
    Button submit_button;

    public static final String PORT_NUM = "PORT_NUM";
    public static final String IP_STRING = "IP_STRING";
    private static final String TAG = "setup_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipconfig);
        IP_config_Edittext = findViewById(R.id.IP_config);
        port_config_Edittext = findViewById(R.id.port_config);
        submit_button = findViewById(R.id.submit);
        submit_button.setOnClickListener(this);
        Log.i(TAG, "starting");

    }

    public void onClick(View V) {

        if (V.getId() == R.id.submit) {
            Log.i(TAG, "gotem");
            Log.i(TAG, "ip is " + IP_config_Edittext.getText());
            Log.i(TAG, "port is " + port_config_Edittext.getText());

            if ((IP_config_Edittext.getText().toString().length() == 0) || (port_config_Edittext.getText().toString().length() == 0)){
                Toast.makeText(this, "input connection info", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "invalid");
            }

            else {
                String IP_address = IP_config_Edittext.getText().toString();
                String PORT = (port_config_Edittext.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(IP_STRING, IP_address);
                intent.putExtra(PORT_NUM, PORT);
                startActivity(intent);
                Log.i(TAG, "starting");
            }
        }
    }
}
