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
        String PG_email = pgArrayList1.get(position).getPgemailID();
        String PG_contact = pgArrayList1.get(position).getPhoneNumber();
        String PG_rooms = pgArrayList1.get(position).getNumberOfRooms();
        Log.d("Data", PG_name+""+PG_address+position);
        holder.setdata(PG_name, PG_address, PG_email, PG_contact, PG_rooms);

        holder.pg_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.pg_name_tv.getContext(), PG_name+" is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return PG_list.getPgArrayList().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        TextView pg_name_tv;
        TextView pg_address_tv;
        TextView pg_email_tv;
        TextView pg_contact_tv;
        TextView pg_rooms_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pg_name_tv = itemView.findViewById(R.id.pg_name);
            pg_address_tv = itemView.findViewById(R.id.pg_address);
            pg_email_tv = itemView.findViewById(R.id.pg_email);
            pg_contact_tv = itemView.findViewById(R.id.pg_contact);
            pg_rooms_tv = itemView.findViewById(R.id.pg_rooms);
        }

        public void setdata(String pg_name, String pg_address, String pg_email, String pg_contact, String pg_rooms) {
            pg_name_tv.setText(pg_name);
            pg_address_tv.setText("Address :"+pg_address);
            pg_email_tv.setText(pg_email);
            pg_contact_tv.setText(pg_contact);
            pg_rooms_tv.setText("Available Rooms :"+pg_rooms);
        }
    }
}
