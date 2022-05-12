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

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class PG_Adapter extends RecyclerView.Adapter<PG_Adapter.ViewHolder> {



    ArrayList<PG> pgArrayList1;
    static int indexClicked;
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
        holder.pg_name_tv.setText(PG_name);
        holder.pg_address_tv.setText("Address :"+PG_address);
        holder.pg_email_tv.setText(PG_email);
        holder.pg_contact_tv.setText(PG_contact);
        holder.pg_rooms_tv.setText("Available Rooms :"+PG_rooms);

    }

    @Override
    public int getItemCount() {
        return PG_list.getPgArrayList().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int idx = getLayoutPosition();
            Log.d("RecyleView Clicked", "At pos: " + idx);
            indexClicked = idx;
            MainActivity.chatID = pgArrayList1.get(idx).getPgName()+MainActivity.Username;
            Intent intent = new Intent(view.getContext().getApplicationContext(), PG_details.class);
            view.getContext().startActivity(intent);
        }

    }
}
