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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    static EditText latitude_edit;
    static EditText longitude_edit;
    private static int progress;

    public static double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("1st", "location");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        t = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        getlocation = (Button) findViewById(R.id.getlocation);
        fmpc = LocationServices.getFusedLocationProviderClient(this);

        latitude_edit = (EditText) findViewById(R.id.latitude);
        longitude_edit = (EditText) findViewById(R.id.longitude);

        radiusLength = (SeekBar) findViewById(R.id.radiusLength);
        radiusSelected = (TextView) findViewById(R.id.radiusSelected);

        radiusLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
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

                        longitude = add.get(0).getLongitude();
                        latitude = add.get(0).getLatitude();

                        latitude_edit.setText(String.valueOf(latitude));
                        longitude_edit.setText(String.valueOf(longitude));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void searchPGList(){
        double lat = Double.parseDouble(latitude_edit.getText().toString());
        double lon = Double.parseDouble(longitude_edit.getText().toString());


        DatabaseReference reference = MainActivity.database.getReference("PGInformation");
        DatabaseReference pgRef = reference.child("PG");

        Log.d("Function", "here");

        reference.child("PG").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("Task Status", "Unsuccessful", task.getException());
                }
                else {
                    Log.d("Reached", "Reading");
                    Object obj = (task.getResult().getValue());

                    HashMap<String, Object> hashMap;
                    hashMap = (HashMap<String, Object>) obj;
                    for(Map.Entry<String,Object> entry : hashMap.entrySet()){
                        Object pg_info = entry.getValue();
                        HashMap<String, Object> pgInformation = (HashMap<String, Object>) pg_info;
                        double lat1 = 1.2, long1 = 1.2;

                        lat1 = Double.parseDouble((String) (pgInformation.get("latitude")));
                        long1 = Double.parseDouble((String) (pgInformation.get("longitude")));


                        Log.d("Entry Found", lat1+" "+long1);

                        Log.d("Distance Found", distance(lat, lat1, lon, long1)+"");

                        if (distance(lat, lat1, lon, long1) <= radiusLength.getProgress()) {
                            PG pg = new PG();


                            pg.setPgName((String)pgInformation.get("pgName"));
                            //Log.d("PGNAME", pg.getPgName()+" "+(String)pgInformation.get("pgName"));

                            pg.setOwnerName((String)pgInformation.get("ownerName"));
                            pg.setPhoneNumber((String)pgInformation.get("phoneNumber"));
                            pg.setPgemailID((String)pgInformation.get("pgemailID"));
                            pg.setNumberOfRooms((String)pgInformation.get("numberOfRooms"));
                            pg.setCity((String)pgInformation.get("city"));

                            pg.setAddress((String)pgInformation.get("address"));
                            pg.setLongitude((String)pgInformation.get("longitude"));
                            pg.setLatitude((String)pgInformation.get("latitude"));
                            pg.setSingleSharingRent((String)pgInformation.get("singleSharingRent"));
                            pg.setDoubleSharingRent((String)pgInformation.get("doubleSharingRent"));
                            pg.setTripleSharingRent((String)pgInformation.get("tripleSharingRent"));

                            pg.setWifiAvailable((Boolean)(pgInformation.get("wifiAvailable")));
                            pg.setGeyserAvailable((Boolean)(pgInformation.get("geyserAvailable")));
                            pg.setParkingAvailable((Boolean)(pgInformation.get("parkingAvailable")));
                            pg.setSecurityAvailable((Boolean)(pgInformation.get("securityAvailable")));
                            pg.setACAvailable((Boolean)(pgInformation.get("acavailable")));

                            pg.setPgID((String)pgInformation.get("pgID"));
                            PG_list.addPG(pg);
                            Log.d("Added Successfully", PG_list.getPgArrayList().size()+"");
                        }

                    }

                    Log.d("Data read", "Completed");

                }
                Log.d("Data read", "Completed_fully");

                Intent intent = new Intent(Search_Filter.this,Recycler_View.class);
                startActivity(intent);
            }

        });




    }

    public void OnSearchClicked(View view){
        System.out.println("Search reached");
        try {
            searchPGList();
        }
        catch (Exception e){
            Log.d("Error Found", e.toString());
        }
        Log.d("Trying to start", "Recyler View");


    }


    public void mapClicked(View view){
        Intent intent = new Intent(Search_Filter.this, MapActivity.class);
        intent.putExtra("activity", "search");
        startActivity(intent);
    }

}