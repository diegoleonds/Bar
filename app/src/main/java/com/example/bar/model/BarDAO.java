package com.example.bar.model;

import android.content.Context;

import java.util.ArrayList;

public class BarDAO {

    private ArrayList<String> bares = new ArrayList<>();

    private Context context;

    public BarDAO(ArrayList<String> bares, Context context) {
        this.bares = bares;
        this.context = context;
    }

    public ArrayList<String> getBares() {
        return bares;
    }

    public void setBares(ArrayList<String> bares) {
        this.bares = bares;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    
}
