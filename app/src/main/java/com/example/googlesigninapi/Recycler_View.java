package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class Recycler_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_container);
        Log.d("Recycle View", "Started");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        PG_Adapter adapter = new PG_Adapter(PG_list.getPgArrayList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        PG_list.deletePGList();
    }
}

// Location of Gauri Shankar
//28.635686999999
// 77.144071500000