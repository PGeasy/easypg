package com.example.googlesigninapi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recycle_View_Chat extends AppCompatActivity {

    private Button send;
    private EditText message;
    private String PG_name;
    static Chat_Adapter adapter;

    static ArrayList<String> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view1);
        send = findViewById(R.id.sendButton);
        message = findViewById(R.id.editMessage);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_container1);
        Log.d("Recycle View", "Started of chat");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
         messages = new ArrayList<String>();

        DatabaseReference reference = MainActivity.database.getReference("PGInformation");
        String id = MainActivity.chatID;

        reference.child("Chat").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("Task Status", "Unsuccessful", task.getException());
                } else {

                    Object obj = (task.getResult().getValue());

                    HashMap<String, String> hashMap;
                    hashMap = (HashMap<String, String>) obj;
                    if(obj == null)
                        return;
                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        String chat_message = entry.getValue();
                        messages.add(chat_message);
                    }
                }
            }
        });
        adapter = new Chat_Adapter();
        recyclerView.setAdapter(adapter);
    }

    public void sentButton(View view){
        String text = MainActivity.Username + "\n" + message.getText().toString();
        message.setText("");
        DatabaseReference reference = MainActivity.database.getReference("PGInformation");
        DatabaseReference chatRef = reference.child("Chat");
        String key = chatRef.child(MainActivity.chatID).push().getKey();

        try {
            reference.child("Chat").child(MainActivity.chatID).child(key).setValue(text);
            messages.add(text);
            adapter.notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d("ErrorMessage", e.toString());
        }

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        //PG_list.deletePGList();
    }
}