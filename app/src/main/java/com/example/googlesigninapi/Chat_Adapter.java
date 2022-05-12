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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.ViewHolder>{



    public Chat_Adapter(){

    }

    @NonNull
    public Chat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, parent, false);
        return new Chat_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chat_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        String chat_message = Recycle_View_Chat.messages.get(position);

        String text = chat_message;
        holder.setdata(text);

    }

    @Override
    public int getItemCount() {
        return Recycle_View_Chat.messages.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        TextView chatBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chatBox = itemView.findViewById(R.id.chatbox);
        }

        public void setdata(String text) {
            chatBox.setText(text);
        }
    }
}
