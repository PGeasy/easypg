package com.example.googlesigninapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PG_details extends AppCompatActivity {


    private TextView PG_name;
    private TextView PG_facilities;
    private TextView PG_details;
    private Button whatsAppOpen;
    private Button paymentOpen;
    private Button chatButton;
    private PG pg;
    private ImageView pg_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_details);
        pg = PG_list.getPgArrayList().get(PG_Adapter.indexClicked);
        PG_name = findViewById(R.id.PG_name);
        PG_name.setText(pg.getPgName());
        pg_image = findViewById(R.id.pgImage);
        chatButton = findViewById(R.id.chatButton);
        PG_name.setText(pg.getPgName());
        PG_details = findViewById(R.id.PG_details);

        whatsAppOpen = findViewById(R.id.whatsapp_open);
        paymentOpen = findViewById(R.id.payment_open);
        // Create a storage reference from our app


// Create a reference with an initial file path and name
        Log.d("Image Location", "images/"+pg.getPgID()+" "+pg.pgID);
        StorageReference pathReference = MainActivity.reference.child("images/"+pg.getPgID());

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Log.d("Download Status", "Success");

                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(pg_image);
                //pg_image.setImageURI(uri);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("Download Status", "Failure");
            }
        });
        showDetails();
    }

    public void showDetails(){
        String dets = "";
        dets += "PG Name is: "+pg.getPgName()+"\n";
        dets += "PG ID is "+pg.getPgID()+"\n";
        dets += "Owner Name is: " + pg.getOwnerName()+"\n";
        dets += "Contact Number: "+pg.getPhoneNumber()+"\n";
        dets += "PG email ID: "+pg.getPgemailID()+"\n";
        dets += "Number of Rooms: "+pg.getNumberOfRooms()+"\n";
        dets += "Number of Vacant Rooms: "+pg.getNumberOfRoomsVacant()+"\n";
        dets += "Longitude of PG: "+pg.getLongitude() +"\n";
        dets += "Latitude of PG: "+pg.getLatitude() + "\n";
        dets += "Single Sharing Rent is: "+pg.getSingleSharingRent()+"\n";
        dets += "Double Sharing Rent is: "+pg.getDoubleSharingRent()+"\n";
        dets += "Triple Sharing Rent is: "+pg.getTripleSharingRent()+"\n";
        dets += "\n\n";

        dets += "Facility Availability"+"\n";
        dets += "IsWifiAvailable: "+pg.isWifiAvailable()+"\n";
        dets += "IsGeyserAvailable: "+pg.isGeyserAvailable()+"\n";
        dets += "IsFoodAvailable: "+pg.isFoodAvailable()+"\n";
        dets += "IsSecurityAvailable: "+pg.isSecurityAvailable()+"\n";
        dets += "IsParkingAvailable: "+pg.isParkingAvailable()+"\n";
        dets += "IsACAvailable: "+pg.isACAvailable()+"\n";

        PG_details.setText(dets);
    }

    public void whatsAppClicked(View view){
        String contact_number = pg.getPhoneNumber();
        String text = "Hi, I really like your PG. Is it available?"+"\n"+"Also is it unisex?";
        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + contact_number + "&text=" + text);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(sendIntent);
    }

    public void bookPGClicked(View view){
        String url = "http://m.p-y.tm";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void chatClicked(View view){
        Intent intent = new Intent(PG_details.this, Recycle_View_Chat.class);
        startActivity(intent);
    }


}