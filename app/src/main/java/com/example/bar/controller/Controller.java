package com.example.bar.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.bar.view.BarActivity;

public class Controller {

    private Context context;

    public Controller(Context context){

        this.context = context;
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
