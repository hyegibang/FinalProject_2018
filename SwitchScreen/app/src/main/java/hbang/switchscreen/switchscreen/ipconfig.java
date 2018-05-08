/*
Hyegi Bang, Jonathan Zerez
SoftDes, Spring 2018

This is the initial activity to our mobile app. It asks the user to provide HOST and PORT
information and then passes that information to MainActivity.java, which actually starts the
communication link.
 */

//Import Dependencies
package hbang.switchscreen.switchscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ipconfig extends AppCompatActivity implements View.OnClickListener{
    //Initialize view Objects and other variables
    EditText IP_config_Edittext;
    EditText port_config_Edittext;
    Button submit_button;
    public static final String PORT_NUM = "PORT_NUM";
    public static final String IP_STRING = "IP_STRING";
    private static final String TAG = "setup_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hbang.switchscreen.switchscreen.R.layout.activity_ipconfig);
        //Pair view objects with their xml counterparts.
        IP_config_Edittext = findViewById(hbang.switchscreen.switchscreen.R.id.IP_config);
        port_config_Edittext = findViewById(hbang.switchscreen.switchscreen.R.id.port_config);
        submit_button = findViewById(hbang.switchscreen.switchscreen.R.id.submit);
        submit_button.setOnClickListener(this);


    }

    public void onClick(View V) {
        /*
        This method is called when the submit button is pressed. It takes the text from the EditText
        objects above, and starts a new intent(MainActivity.java) while simultaneously passing that
        class the HOST and PORT information.
         */
        if (V.getId() == hbang.switchscreen.switchscreen.R.id.submit) {
            if ((IP_config_Edittext.getText().toString().length() == 0) || (port_config_Edittext.getText().toString().length() == 0)){
                Toast.makeText(this, "input connection info", Toast.LENGTH_SHORT).show();
            }
            else {
                String IP_address = IP_config_Edittext.getText().toString();
                String PORT = (port_config_Edittext.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(IP_STRING, IP_address);
                intent.putExtra(PORT_NUM, PORT);
                startActivity(intent);
            }
        }
    }
}
