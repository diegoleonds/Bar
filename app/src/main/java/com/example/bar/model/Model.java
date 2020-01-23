package com.example.bar.model;

import android.content.Context;

public class Model {

    private Context context;
    private BarDAO barDAO;

    public Model(Context context){

        this.context = context;
        barDAO = new BarDAO(context);
    }

    public long insertBar(Bar bar){

        return barDAO.inserirBar(bar);
    }



}
