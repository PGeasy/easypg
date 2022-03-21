package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterPgPage extends AppCompatActivity {
    private Toast t;
    static ArrayList<String> arr;
    private TextView et1, et2,et3,et4, et12, et5,et6,et7,et8,et9,et10,et11;
    private RadioButton ac, wifi,geyser, food,parking,security;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pg_page);

        t = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
        Button submit = (Button) findViewById(R.id.submit);

        et1  = (TextView) findViewById(R.id.et1);
        et2  = (TextView) findViewById(R.id.et2);
        et3  = (TextView) findViewById(R.id.et3);
        et4  = (TextView) findViewById(R.id.et4);
        et5  = (TextView) findViewById(R.id.et5);
        et6  = (TextView) findViewById(R.id.et6);
        et7  = (TextView) findViewById(R.id.et7);
        et8  = (TextView) findViewById(R.id.et8);
        et9  = (TextView) findViewById(R.id.et9);
        et10  = (TextView) findViewById(R.id.et10);
        et11 = (TextView) findViewById(R.id.et11);
        et12  = (TextView) findViewById(R.id.et12);
        ac= (RadioButton) findViewById(R.id.ac);
        wifi= (RadioButton) findViewById(R.id.wifi);
        parking= (RadioButton) findViewById(R.id.parking);
        geyser= (RadioButton) findViewById(R.id.geyser);
        security= (RadioButton) findViewById(R.id.security);
        food= (RadioButton) findViewById(R.id.food);


        RegisterPgPage.arr = new ArrayList<String>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterPgPage.arr.add(et1.getText().toString());
                RegisterPgPage.arr.add(et2.getText().toString());
                RegisterPgPage.arr.add(et3.getText().toString());
                RegisterPgPage.arr.add(et4.getText().toString());
                RegisterPgPage.arr.add(et12.getText().toString());
                RegisterPgPage.arr.add(et5.getText().toString());
                RegisterPgPage.arr.add(et6.getText().toString());
                RegisterPgPage.arr.add(et7.getText().toString());
                RegisterPgPage.arr.add(et8.getText().toString());
                RegisterPgPage.arr.add(et9.getText().toString());
                RegisterPgPage.arr.add(et10.getText().toString());
                RegisterPgPage.arr.add(et11.getText().toString());
                Log.d("data",RegisterPgPage.arr.toString());

            }
        });

    }
}