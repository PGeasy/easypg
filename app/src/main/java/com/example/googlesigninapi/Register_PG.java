package com.example.googlesigninapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class Register_PG extends AppCompatActivity {

    static TextView et1, et2,et3,et4, et12, et5,et6,et7,et8,et9,et10,et11;
    private RadioButton ac, wifi,geyser, food,parking,security;
    public Uri imageUri;
    public PG pg;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pg);

        Button submit = (Button) findViewById(R.id.submit);

        et1 = (TextView) findViewById(R.id.et1);
        et2 = (TextView) findViewById(R.id.et2);
        et3 = (TextView) findViewById(R.id.et3);
        et4 = (TextView) findViewById(R.id.et4);
        et5 = (TextView) findViewById(R.id.et5);
        et6 = (TextView) findViewById(R.id.et6);
        et7 = (TextView) findViewById(R.id.et7);
        et8 = (TextView) findViewById(R.id.et8);
        et9 = (TextView) findViewById(R.id.et9);
        et10 = (TextView) findViewById(R.id.et10);
        et11 = (TextView) findViewById(R.id.et11);
        et12 = (TextView) findViewById(R.id.et12);
        ac = (RadioButton) findViewById(R.id.ac);
        wifi = (RadioButton) findViewById(R.id.wifi);
        parking = (RadioButton) findViewById(R.id.parking);
        geyser = (RadioButton) findViewById(R.id.geyser);
        security = (RadioButton) findViewById(R.id.security);
        food = (RadioButton) findViewById(R.id.food);

        Log.d("Reached1", "Here");

    }

    public void addImage(View view){
        choosepicture();
    }

    private void choosepicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            uploadPicture();

        }
    }


    private void uploadPicture() {
        key = UUID.randomUUID().toString();
        StorageReference storageReference = MainActivity.reference.child("images/" + key);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("Upload Status", "Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Upload Status: ", "Failure");
            }
        });
    }


    public void onClickSubmit(View view) {

        DatabaseReference reference = MainActivity.database.getReference("PGInformation");

        DatabaseReference pgRef = reference.child("PG");


        Log.d("Reached", "Here");
        if(pg == null)
            pg = new PG();

        pg.setPgName(et1.getText().toString());
        pg.setOwnerName(et2.getText().toString());
        pg.setPhoneNumber(et3.getText().toString());
        pg.setPgemailID(et4.getText().toString());
        pg.setNumberOfRooms(et12.getText().toString());
        pg.setCity(et5.getText().toString());

        Log.d("Data", "successful");

        pg.setAddress(et6.getText().toString());
        pg.setLatitude(et7.getText().toString());
        pg.setLongitude(et8.getText().toString());
        pg.setSingleSharingRent(et9.getText().toString());
        pg.setDoubleSharingRent(et10.getText().toString());
        pg.setTripleSharingRent(et11.getText().toString());

        pg.setWifiAvailable(wifi.isChecked());
        pg.setGeyserAvailable(geyser.isChecked());
        pg.setParkingAvailable(parking.isChecked());
        pg.setSecurityAvailable(security.isChecked());
        pg.setACAvailable(ac.isChecked());

        pg.setPgID(key);

        Log.d("PG ID is", pg.getPgID());



        try {
            reference.child("PG").child(key).setValue(pg);
        }
        catch (Exception e){
            Log.d("ErrorMessage", e.toString());
        }
        Log.d("Reached", "Before Reading");


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


    public void locationDetailsClicked(View view){
        Intent intent = new Intent(Register_PG.this, MapActivity.class);
        intent.putExtra("activity", "register");
        startActivity(intent);
    }

}