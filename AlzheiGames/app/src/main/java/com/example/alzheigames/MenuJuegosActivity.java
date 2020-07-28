package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuJuegosActivity extends AppCompatActivity {

    Button irAdivina,irMemorama,irRompecabeza;
    ImageView memorama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juegos);
        irMemorama = (Button) findViewById(R.id.btnMemorama);
        irMemorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser=getIntent().getStringExtra("idUser");
                Intent memor = new Intent(MenuJuegosActivity.this, MemoramaMainActivity.class);
                memor.putExtra("idUser",idUser);
                startActivity(memor);
            }
        });
        irAdivina = (Button)findViewById(R.id.btnJugarAdivina);
        irAdivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser=getIntent().getStringExtra("idUser");
                Intent irJugarAdivina = new Intent(MenuJuegosActivity.this, juegoAdivina.class);
                irJugarAdivina.putExtra("idUser",idUser);
                startActivity(irJugarAdivina);
            }
        });

        irRompecabeza = (Button)findViewById(R.id.btnRompeCa);
        irRompecabeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser=getIntent().getStringExtra("idUser");
                Intent irJugarRompeCa = new Intent(MenuJuegosActivity.this, RompecabezasActivity.class);
                irJugarRompeCa.putExtra("idUser",idUser);
                startActivity(irJugarRompeCa);
            }
        });

    }


}