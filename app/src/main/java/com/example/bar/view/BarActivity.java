package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bar.R;
import com.example.bar.controller.AdapterProdutos;
import com.example.bar.controller.Controller;

public class BarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterProdutos adapterProdutos;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        int idBar = getIntent().getIntExtra("id", 1);

        recyclerView = new RecyclerView(this);
        adapterProdutos = new AdapterProdutos(this, String.valueOf(idBar));
        controller = new Controller(this);

        recyclerView.setAdapter(adapterProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        controller.listaProdutos(adapterProdutos);
    }
}
