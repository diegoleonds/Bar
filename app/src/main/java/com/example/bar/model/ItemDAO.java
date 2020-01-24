package com.example.bar.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ItemDAO {

    private Conexao conexao;
    private SQLiteDatabase database;

    private final String[] colunas = new String[]{"id", "fkBar", "fkProduto", "preco"};
    private final String tabela = "item";

    public ItemDAO(Context context){

        conexao = new Conexao(context);
        database = conexao.getWritableDatabase();
    }

    public long insertItem(Item item){

        ContentValues values = new ContentValues();

        values.put ("fkBar", item.getFkBar());
        values.put("fkProduto", item.getFkProduto());
        values.put("preco", item.getPreco());

        return database.insert(tabela, null, values);
    }

    public Item getItem(String id){

        String selection = "id" + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = database.query(tabela, colunas,
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {

            return getItem(cursor);
        }

        return null;
    }

    public Item getItem(String fkBar, String fkProduto){

        String selection = "fkProduto = ? AND fkBar = ?";
        String[] selectionArgs = {fkProduto, fkBar};

        Cursor cursor = database.query(tabela, colunas,
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {

            return getItem(cursor);
        }

        return null;
    }

    public List<Item> getItensDoBar(String fkBar){

        String selection = "fkBar" + " = ?";
        String[] selectionArgs = {fkBar};

        Cursor cursor = database.query(tabela, colunas,
                selection, selectionArgs, null, null, null);

        List<Item> itens = new ArrayList<>();

        while (cursor.moveToNext()) {

            itens.add(getItem(cursor));
        }

        return itens;
    }

    private Item getItem(Cursor cursor){

        Item item = new Item();
        item.setId(cursor.getInt(0));
        item.setFkBar(cursor.getInt(1));
        item.setFkProduto(cursor.getInt(2));
        item.setPreco(cursor.getDouble(3));
        return item;
    }
}
