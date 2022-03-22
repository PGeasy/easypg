package com.example.googlesigninapi;

import java.util.ArrayList;

class PG_list {


    private static ArrayList<PG> pgArrayList;

    private PG_list(){

    }

    public static ArrayList<PG> getPgArrayList(){
        if(pgArrayList == null){
            pgArrayList = new ArrayList<>();
        }
        return pgArrayList;
    }

    public static void addPG(PG pg){
        if(pgArrayList == null) pgArrayList = new ArrayList<>();
        pgArrayList.add(pg);
    }

    public void deletePGList(){
        pgArrayList = null;
    }


}
