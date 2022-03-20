package com.example.googlesigninapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Register_PG extends AppCompatActivity {

    static ArrayList<String> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pg);

        TextView et1  = (TextView) findViewById(R.id.et1);
        TextView et2  = (TextView) findViewById(R.id.et2);
        TextView et3  = (TextView) findViewById(R.id.et3);
        TextView et4  = (TextView) findViewById(R.id.et4);
        TextView et5  = (TextView) findViewById(R.id.et5);
        TextView et6  = (TextView) findViewById(R.id.et6);
        TextView et7  = (TextView) findViewById(R.id.et7);
        TextView et8  = (TextView) findViewById(R.id.et8);
        TextView et9  = (TextView) findViewById(R.id.et9);
        TextView et10  = (TextView) findViewById(R.id.et10);
        TextView et11 = (TextView) findViewById(R.id.et11);
        TextView et12  = (TextView) findViewById(R.id.et12);

        Button submit = (Button) findViewById(R.id.submit);
        arr = new ArrayList<String>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.add(et1.getText().toString());
                arr.add(et2.getText().toString());
                arr.add(et3.getText().toString());
                arr.add(et4.getText().toString());
                arr.add(et5.getText().toString());
                arr.add(et6.getText().toString());
                arr.add(et7.getText().toString());
                arr.add(et8.getText().toString());
                arr.add(et9.getText().toString());
                arr.add(et10.getText().toString());
                arr.add(et11.getText().toString());
                arr.add(et12.getText().toString());
                Log.d("Sandy", arr.toString());

                AlertDialog builder1 = new AlertDialog.Builder(Register_PG.this)
                        .setTitle("DATA ADDED SUCCESSFULLY")
                        .setMessage("Click OK to continue...")
                        .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).create();
                builder1.show();
            }
        });
    }
}