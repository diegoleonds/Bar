package com.example.bar.model;

import android.content.Context;

import java.util.List;

public class Model {

    private BarDAO barDAO;
    private ProdutoDAO produtoDAO;

    public Model(Context context){

        barDAO = new BarDAO(context);
        produtoDAO = new ProdutoDAO(context);
    }

    public long insertBar(Bar bar){

        return barDAO.inserirBar(bar);
    }

    public List<Bar> getBares(){

        return barDAO.meDAOsBar();
    }

    public void deleteBar(Bar b) {

        barDAO.excluir(b);
    }

    public Produto getProduto(String id){

        return produtoDAO.getProduto(id);
    }

    public List getProdutos(){

        return produtoDAO.getProdutos();
    }
}
