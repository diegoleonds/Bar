package com.example.bar.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Só para pegar os registros da tabela produto
 * (ver classe Conexao)
 *
 * (no momento o projeto usa produtos pré cadastrados,
 * então NÃO fazer métodos de insert, update e delete)
 *
 * @author Diego <diego.santos@hbsis.com.br>
 */

public class ProdutoDAO {

    private Conexao conexao;
    private SQLiteDatabase database;
    private final String[] colunas = new String[]{"id", "nome", "idImagem"};
    private final String tabela = "produto";

    public ProdutoDAO(Context context){

        conexao = new Conexao(context);
        database = conexao.getReadableDatabase();
    }

    public Produto getProduto(String id){

        /**
         * where id = ?
         */
        String selection = "id" + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = database.query(tabela, colunas,
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            Produto p = new Produto();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setIdImagem(cursor.getInt(2));
            return p;
        }

        return null;
    }

    public List<Produto> getProdutos(){

        List<Produto> produtos = new ArrayList<>();

        Cursor cursor = database.query(tabela, colunas,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Produto p = new Produto();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setIdImagem(cursor.getInt(2));
            produtos.add(p);
        }

        return produtos;
    }

}
