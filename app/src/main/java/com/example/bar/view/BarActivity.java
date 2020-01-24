package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bar.R;
import com.example.bar.model.Bar;
import com.example.bar.model.BarDAO;

public class BarActivity extends AppCompatActivity {

    private Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        extra = getIntent().getExtras();

        String id = String.valueOf(extra.getInt("id"));
        BarDAO barDAO = new BarDAO(this);

        Bar bar = barDAO.meDAOBar(id);

        setTitle(bar.getNome());

    }

}
