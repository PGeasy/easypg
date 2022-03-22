package com.example.googlesigninapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PG_Adapter extends RecyclerView.Adapter<PG_Adapter.ViewHolder> {



    ArrayList<PG> pgArrayList1;

    /*public PG_Adapter() {
        this.pgArrayList1 = PG_list.getPgArrayList();
    }*/

    public PG_Adapter(ArrayList<PG> pgs){
        Log.d("Reached Here", "Adapter"+pgs.size());
        this.pgArrayList1 = pgs;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PG_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String PG_name = pgArrayList1.get(position).getPgName();
        String PG_address = pgArrayList1.get(position).getAddress();
        Log.d("Data", PG_name+""+PG_address+position);
        holder.setdata(PG_name, PG_address);

        holder.movie_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.movie_name_tv.getContext(), "Movie "+(position+1)+" is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return PG_list.getPgArrayList().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        TextView movie_name_tv;
        TextView movie_date_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_name_tv = itemView.findViewById(R.id.movie_name);
            movie_date_tv = itemView.findViewById(R.id.movie_date);
        }

        public void setdata(String movie_name, String movie_date) {
            movie_name_tv.setText(movie_name);
            movie_date_tv.setText(movie_date);
        }
    }
}
