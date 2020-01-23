package com.example.bar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bar.R;
import com.example.bar.controller.AdapterBares;
import com.example.bar.controller.Controller;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterBares adapterBares;
    private Controller controller;

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
    }
}
