package com.example.bar.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";

    /**
     * Versão do banco.
     * Quando for feita qualquer alteração no esquema
     * a versão dever ser incrementada.
     */
    private static final int version = 1;
    public Conexao(Context context) {
        super(context, name, null, version);
    }

    /**
     * Só roda uma vez. Alterações no esquema vão rodar
     * no onUpgrade.
     *
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE bar" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(100)," +
                "endereco VARCHAR(150),"+
                "classificacao DOUBLE(5))");

        db.execSQL("CREATE TABLE produto" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(80))");

        db.execSQL("CREATE TABLE valores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fkBar INTEGER," +
                "fkProduto INTEGER, " +
                "preco REAL," +
                "FOREIGN KEY(fkProduto) REFERENCES produto," +
                "FOREIGN KEY(fkBar) REFERENCES bar)");
    }

    /**
     * Fazer as alterações do esquema aqui.
     *
     * @param db
     * @param oldVersion comparar com o atributo version, jogar num bloco if
     *                   e alterar o esquema.
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

    }
}