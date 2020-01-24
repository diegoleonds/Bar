package com.example.bar.model;

import android.content.Context;

import java.util.List;

public class Model {

    private BarDAO barDAO;
    private ProdutoDAO produtoDAO;
    private ItemDAO itemDAO;

    public Model(Context context){

        barDAO = new BarDAO(context);
        produtoDAO = new ProdutoDAO(context);
        itemDAO = new ItemDAO(context);
    }

    public static String getValorFormatado(Context context, String fkBar, String idProduto){

        Conexao conexao = new Conexao(context);
        ItemDAO itemDAO = new ItemDAO(context);

        String r;
        Item i = itemDAO.getItem(fkBar, idProduto);

        r = String.valueOf(i.getPreco());

        return r;
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

    public void insertItem(Item item){

        itemDAO.insertItem(item);
    }

    public List<Item> getItensDoBar(String fkBar){

        return itemDAO.getItensDoBar(fkBar);
    }
}
