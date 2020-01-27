package com.example.bar.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bar.model.Bar;
import com.example.bar.model.Item;
import com.example.bar.model.BarDAO;
import com.example.bar.model.Model;
import com.example.bar.model.Produto;
import com.example.bar.view.BarActivity;
import com.example.bar.view.CadastroBars;

import java.util.ArrayList;

public class Controller {

    private Context context;
    private Model model;

    public Controller(Context context){

        this.context = context;
        model = new Model(context);
    }

    public void listaProdutos(AdapterProdutos adapterProdutos){

        ArrayList<Produto> aux = new ArrayList<>();
        aux.addAll(model.getProdutos());

        adapterProdutos.setProdutos(aux);
        adapterProdutos.notifyDataSetChanged();
    }

    public void insertBar(Bar bar){

        model.insertBar(bar);
    }

    public void getBares(AdapterBares bars){

        BarDAO barsDao = new BarDAO(context);

        ArrayList<Bar> aux = new ArrayList<Bar>();
        aux.addAll(barsDao.meDAOsBares());
        bars.setBares(aux);
        bars.notifyDataSetChanged();
    }

    public Integer getIdLivre(){

        BarDAO barDAO = new BarDAO(context);
        ArrayList<Bar> aux = new ArrayList<Bar>();

        try {

            aux.addAll(barDAO.meDAOsBares());
            return aux.get(aux.size() - 1).getId() + 1;

        } catch (Exception e){

        }

        return 1;
    }

    public void abrirCadastro(){

        Intent intent = new Intent(context, CadastroBars.class);
        context.startActivity(intent);

    }

    public Click clicouNoBar(){

        return new Click() {
            @Override
            public void clicou(Integer id) {

                Bundle b = new Bundle();
                b.putInt("id", id);


                Intent i = new Intent(context, BarActivity.class);
                i.putExtras(b);

                context.startActivity(i);
            }

            @Override
            public void clicou(Integer id, Integer idLivre, TextView textView) {

            }

        };
    }

    public void insertItem(Item item){

        model.insertItem(item);
    }

    public void listarItensDobar(String fkBar, AdapterProdutos adapterProdutos){


    }

    public Click clicouNoProduto(){

        return new Click() {
            @Override
            public void clicou(Integer id) {

            }

            @Override
            public void clicou(final Integer id, final Integer idLivre, final TextView textView) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Preço:");

                final EditText input = new EditText(context);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);


                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Item i = new Item();
                        i.setFkProduto(id);
                        i.setFkBar(idLivre);
                        i.setPreco(Double.parseDouble(String.valueOf(input.getText())));

                        model.insertItem(i);

                        textView.setText(String.valueOf(textView.getText()) + " " +
                                i.getPreco());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        };
    }
}
