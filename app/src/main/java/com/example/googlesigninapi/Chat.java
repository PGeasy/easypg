package com.example.googlesigninapi;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Chat {
    private String ChatID;
    private ArrayList<String> text;
    private ArrayList<String> writer;

    public Chat(String PG_name, String User){
        ChatID = PG_name+User;
        text = new ArrayList<>();
        writer = new ArrayList<>();
    }

    public String getChatID() {
        return ChatID;
    }

    public void setChatID(String chatID) {
        ChatID = chatID;
    }

    public ArrayList<String> getWriter() {
        return writer;
    }

    public void addMessage(String text1){
        text.add(text1);
    }

    public void addWriter(String write){
        writer.add(write);
    }

    public void setWriter(ArrayList<String> writer) {
        this.writer = writer;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }
}
