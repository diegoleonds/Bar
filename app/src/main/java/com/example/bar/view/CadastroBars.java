package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bar.R;
import com.example.bar.model.Bar;
import com.example.bar.model.BarDAO;

public class CadastroBars extends AppCompatActivity {

    private BarDAO barDAO;
    private EditText nomeBar;
    private EditText enderecoBar;
    private Button btnAdcBar;
    private RatingBar classifica;
    private TextView txtValorClassificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bars);

        nomeBar = findViewById(R.id.Nome);
        enderecoBar = findViewById(R.id.Endereco);
        btnAdcBar = findViewById(R.id.btCadastrar);

        classificar();

        barDAO = new BarDAO(this);

        classifica = (RatingBar) findViewById(R.id.estrelas);
        btnAdcBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraBar();
                Toast.makeText(CadastroBars.this,""+nomeBar.getText().toString()+" cadastrado",Toast.LENGTH_SHORT).show();
                Toast.makeText(CadastroBars.this, String.valueOf(classifica.getRating()),Toast.LENGTH_SHORT).show();

                finish();
            }

        });

    }
    public void classificar() {
        classifica = (RatingBar) findViewById(R.id.estrelas);
        txtValorClassificacao = (TextView) findViewById(R.id.valorClassificacao);

        classifica.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar classifica, float avaliacao, boolean fromUser) {
                txtValorClassificacao.setText(String.valueOf(avaliacao));

            }
        });
    }

    public void cadastraBar() {

        Bar b = new Bar();
        b.setNome(nomeBar.getText().toString());
        b.setEndereco(enderecoBar.getText().toString());
        b.setClassificacao(Double.parseDouble(txtValorClassificacao.getText().toString()));
        barDAO.inserirBar(b);

    }
}





