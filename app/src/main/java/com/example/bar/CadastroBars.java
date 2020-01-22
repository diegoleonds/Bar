package com.example.bar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bar.model.Bar;
import com.example.bar.model.BarDAO;

public class CadastroBars extends AppCompatActivity {

    private BarDAO barDAO;
    private EditText nomeBar;
    private EditText enderecoBar;
    private Button btnAdcBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bars);

        nomeBar = findViewById(R.id.Nome);
        enderecoBar = findViewById(R.id.Endereco);
        btnAdcBar = findViewById(R.id.btCadastrar);

        barDAO = new BarDAO(this);

        btnAdcBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraBar();
                Toast.makeText(CadastroBars.this,""+nomeBar.getText().toString()+" cadastrado",Toast.LENGTH_SHORT).show();


            }

        });

    }

    public void cadastraBar() {

        Bar b = new Bar();
        b.setNome(nomeBar.getText().toString());
        b.setEndereco(enderecoBar.getText().toString());
        barDAO.inserirBar(b);

    }





}
