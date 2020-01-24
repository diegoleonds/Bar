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
        values.put("classificacao", bar.getClassificacao());
        return dados.insert("bar", null, values);
    }

    public void atualizarBar (Bar bar){
        int id = bar.getId();
        ContentValues values = new ContentValues();
        values.put("nome", bar.getNome());
        values.put("endereco", bar.getEndereco());
        values.put("classificacao", bar.getClassificacao());
        dados.update("bar", values, "id = ?", new
                String[]{bar.getId().toString()});
    }

    public List<Bar> meDAOsBares() {

        List<Bar> bar = new ArrayList<>();

        Cursor cursor = dados.query("bar", new String[]{"id", "nome", "endereco","classificacao"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Bar b = new Bar();
            b.setId(cursor.getInt(0));
            b.setNome(cursor.getString(1));
            b.setEndereco(cursor.getString(2));
            b.setClassificacao(cursor.getDouble(3));
            bar.add(b);
        }
        return bar;
    }

    public Bar meDAOBar(String id){

        String selection = "id" + " = ?";

        String[] selectionArgs = {id};

        Cursor cursor = dados.query("bar",new String[]{"id", "nome", "endereco","classificacao"},
                selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()){
            Bar b = new Bar();
            b.setId(cursor.getInt(0));
            b.setNome(cursor.getString(1));
            b.setEndereco(cursor.getString(2));
            b.setClassificacao(cursor.getDouble(3));
            return b;
        }
        return null;
    }

    public void excluir(Bar b) {

        dados.delete("bar", "id = ?", new String[]{b.getId().toString()});

    }
}
