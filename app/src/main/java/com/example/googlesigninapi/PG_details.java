package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PG_details extends AppCompatActivity {


    private TextView PG_name;
    private TextView PG_facilities;
    private TextView PG_details;
    private Button whatsAppOpen;
    private Button paymentOpen;
    private Button chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_details);
        PG pg = PG_list.getPgArrayList().get(PG_Adapter.indexClicked);
        PG_name = findViewById(R.id.PG_name);
        chatButton = findViewById(R.id.chatButton);
        PG_name.setText(pg.getPgName());
        PG_facilities = findViewById(R.id.PG_facilities);
        PG_details = findViewById(R.id.PG_details);
        PG_details.setText(pg.getPgemailID()+"\n"+pg.getAddress());
        whatsAppOpen = findViewById(R.id.whatsapp_open);
        paymentOpen = findViewById(R.id.payment_open);
    }

    public void whatsAppClicked(View view){
        String contact_number = "+918468019585";
        String text = "Hi, I really like your PG. Is it available?";
        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + contact_number + "&text=" + text);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(sendIntent);
    }

    public void bookPGClicked(View view){

    }

    public void chatClicked(View view){
        Intent intent = new Intent(PG_details.this, Recycle_View_Chat.class);
        startActivity(intent);
    }


}