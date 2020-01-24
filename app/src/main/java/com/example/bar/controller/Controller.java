package com.example.bar.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.bar.model.Bar;
import com.example.bar.model.Model;
import com.example.bar.view.BarActivity;

public class Controller {

    private Context context;
    private Model model;

    public Controller(Context context){

        this.context = context;
        model = new Model(context);
    }

    public void listaProdutos(AdapterProdutos adapterProdutos){

        adapterProdutos.getProdutos().addAll(model.getProdutos());
    }

    public void insertBar(Bar bar){

        model.insertBar(bar);
    }

    public Click clicouNoBar(){

        return new Click() {
            @Override
            public void clicou(Integer id) {

                /*
                Bundle b = new Bundle();
                b.putInt("id", id);
                 */

                Intent i = new Intent(context, BarActivity.class);
                //i.putExtras(b);

                context.startActivity(i);
            }
        };
    }

    public Click clicouNoProduto(){

        return new Click() {
            @Override
            public void clicou(Integer id) {


            }
        };
    }
}
