package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Recycler_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Movie_data[] movie_list = new Movie_data[]{
                new Movie_data("Movie1", "Date1"),
                new Movie_data("Movie2", "Date2"),
                new Movie_data("Movie3", "Date3"),
                new Movie_data("Movie4", "Date4"),
                new Movie_data("Movie5", "Date5"),
                new Movie_data("Movie6", "Date6"),
                new Movie_data("Movie7", "Date7"),
                new Movie_data("Movie8", "Date8"),
                new Movie_data("Movie9", "Date9"),
                new Movie_data("Movie10", "Date10")
        };
        PG_Adapter adapter = new PG_Adapter(movie_list);
        recyclerView.setAdapter(adapter);
    }
}
