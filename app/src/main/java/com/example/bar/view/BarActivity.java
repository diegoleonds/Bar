package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.bar.R;
import com.example.bar.model.Bar;
import com.example.bar.model.BarDAO;  
import com.example.bar.controller.AdapterProdutos;
import com.example.bar.controller.Controller;

public class BarActivity extends AppCompatActivity {
    private Bundle extra;
    private RecyclerView recyclerView;
    private AdapterProdutos adapterProdutos;
    private Controller controller;
    private RatingBar estrelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        estrelas = findViewById(R.id.estrelas);
      
        extra = getIntent().getExtras();

        String id = String.valueOf(extra.getInt("id"));
        BarDAO barDAO = new BarDAO(this);

        Bar bar = barDAO.meDAOBar(id);

        setTitle(bar.getNome());
        estrelas.setRating(bar.getClassificacaoAsFloat());

        int idBar = getIntent().getIntExtra("id", 1);

        recyclerView = findViewById(R.id.rv_produto);
        adapterProdutos = new AdapterProdutos(this, String.valueOf(idBar));

        controller = new Controller(this);

        recyclerView.setAdapter(adapterProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        controller.listaProdutos(adapterProdutos);
    }

}
