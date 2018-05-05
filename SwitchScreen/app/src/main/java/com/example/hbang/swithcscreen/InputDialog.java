package com.example.hbang.swithcscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;

public class InputDialog extends AppCompatDialogFragment {
    EditText valueinput;
    PrintWriter pw;


    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.measure_input, null);

        builder.setView(view)
                .setTitle("Smart Dimension")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pw.write("esc");
                        dialog.dismiss();



                    }
                })
                .setPositiveButton("enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String inputVal = valueinput.getText().toString();
                        pw.write(inputVal);
                        System.out.println(inputVal);
                        pw.flush();
                        dialog.dismiss();

                    }
                });

        valueinput = view.findViewById(R.id.valueinput);
        return builder.create();
    }
}

