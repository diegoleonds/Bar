package com.example.bar.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bar.ProjetoMapasActivity;
import com.example.bar.R;
import com.example.bar.controller.AdapterBares;
import com.example.bar.controller.Controller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterBares adapterBares;
    private Controller controller;
    private FloatingActionButton fabiCadastro;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_bares);
        controller = new Controller(this);

        btn = findViewById(R.id.btnmensagem);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(btn.getContext(), Mensagem.class);

                startActivity(intent);
            }
        });

    }

    public void abrir(){

        final int idLivre = controller.getIdLivre();

        fabiCadastro = findViewById(R.id.floatAdd);
        fabiCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(fabiCadastro.getContext(), CadastroBars.class);
                intent.putExtra("idLivre", idLivre);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapterBares = new AdapterBares(this, controller.clicouNoBar());

        rv.setAdapter(adapterBares);
        rv.setLayoutManager(new LinearLayoutManager(this));
        abrir();
        controller.getBares(adapterBares);

    }

    public void testeMapa(View view){
        Intent i = new Intent(this, ProjetoMapasActivity.class);
        startActivity(i);
    }

}
