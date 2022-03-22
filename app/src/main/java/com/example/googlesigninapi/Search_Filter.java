package com.example.googlesigninapi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Search_Filter extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button getlocation;
    TextView showlocation;
    LocationManager locationManager;
    FusedLocationProviderClient fmpc;
    private Double latitude, longitude;
    private Toast t;
    private SeekBar radiusLength;
    private TextView radiusSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("1st", "location");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        t = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        getlocation = (Button) findViewById(R.id.getlocation);
        fmpc = LocationServices.getFusedLocationProviderClient(this);

        radiusLength = (SeekBar) findViewById(R.id.radiusLength);
        radiusSelected = (TextView) findViewById(R.id.radiusSelected);

        radiusLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = i;
                radiusSelected.setText("Radius Selected: "+String.valueOf(progress)+"KM");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Search_Filter.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                    // Checking GPS is enabled
                    if(!(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))){
                        t.setText("Please Enable your GPS First");
                        t.show();
                    }
                    else getLocation();
                } else {

                    ActivityCompat.requestPermissions(Search_Filter.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
            }
        });


    }



    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        }
        fmpc.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location l = task.getResult();
                if (l != null) {
                    Geocoder g = new Geocoder(Search_Filter.this, Locale.getDefault());
                    try {
                        List<Address> add = g.getFromLocation(l.getLatitude(), l.getLongitude(), 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void OnSearchClicked(View view){
        Intent intent = new Intent();

    }

}