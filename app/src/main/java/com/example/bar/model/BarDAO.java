package com.example.bar.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BarDAO {

    private Conexao conexao;
    private SQLiteDatabase dados;
    private Context context;

    public BarDAO(Context context) {

        this.context = context;
        this.conexao = new Conexao(context);
        dados = conexao.getWritableDatabase();
    }

    public long inserirBar(Bar bar){

        ContentValues values = new ContentValues();
        values.put ("nome", bar.getNome());
        values.put("endereco", bar.getEndereco());
        return dados.insert("bar", null, values);
    }

    public void atualizarBar (Bar bar){
        int id = bar.getId();
        ContentValues values = new ContentValues();
        values.put("nome", bar.getNome());
        values.put("endereco", bar.getEndereco());
        dados.update("bar", values, "id = ?", new
                String[]{bar.getId().toString()});
    }

    public List<Bar> meDAOsBar () {

        List<Bar> bar = new ArrayList<>();

        Cursor cursor = dados.query("bar", new String[]{"id", "nome", "endereco"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Bar b = new Bar();
            b.setId(cursor.getInt(0));
            b.setNome(cursor.getString(1));
            b.setEndereco(cursor.getString(2));
            bar.add(b);
        }
        return bar;
    }

    public void excluir(Bar b) {

        dados.delete("bar", "id = ?", new String[]{b.getId().toString()});

    }
}
