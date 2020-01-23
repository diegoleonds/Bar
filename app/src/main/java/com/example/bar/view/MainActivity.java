package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bar.R;
import com.example.bar.controller.AdapterBares;
import com.example.bar.controller.Controller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterBares adapterBares;
    private Controller controller;
    private FloatingActionButton fabiCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_bares);
        controller = new Controller(this);
        adapterBares = new AdapterBares(this, controller.clicouNoBar());

        rv.setAdapter(adapterBares);
        Log.e("aaaaaaaaaa", adapterBares.toString());
        rv.setLayoutManager(new LinearLayoutManager(this));
        abrir();
    }

    public void abrir(){
        fabiCadastro = findViewById(R.id.floatAdd);
        fabiCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.abrirCadastro();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        controller.getBares(adapterBares);
    }

}
