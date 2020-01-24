package com.example.bar.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bar.R;

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
                "nome VARCHAR(80)," +
                "idImagem INTEGER)");

        db.execSQL("CREATE TABLE valores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fkBar INTEGER," +
                "fkProduto INTEGER, " +
                "preco REAL," +
                "FOREIGN KEY(fkProduto) REFERENCES produto," +
                "FOREIGN KEY(fkBar) REFERENCES bar)");

        /**
         * Inserindo produtos na tabela produto,
         */

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", "Pepsi");
        values.put("idImagem", R.drawable.pepsi);

        ContentValues values1 = new ContentValues();
        values1.put("nome", "Guarana");
        values1.put("idImagem", R.drawable.guarana);

        ContentValues values2 = new ContentValues();
        values2.put("nome", "Sukita");
        values2.put("idImagem", R.drawable.sukita);

        ContentValues values3 = new ContentValues();
        values3.put("nome", "Skol");
        values3.put("idImagem", R.drawable.skol);

        ContentValues values4 = new ContentValues();
        values4.put("nome", "Budweiser");
        values4.put("idImagem", R.drawable.budweiser);

        database.insert("produto", null, values);
        database.insert("produto", null, values1);
        database.insert("produto", null, values2);
        database.insert("produto", null, values3);
        database.insert("produto", null, values4);
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