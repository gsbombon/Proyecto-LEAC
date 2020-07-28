package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class juegoAdivina extends AppCompatActivity {

    Button btnJugar,btnRespuestas,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_adivina);
        final String idUser=getIntent().getStringExtra("idUser");
        btnJugar = (Button)findViewById(R.id.btnJugar);
        btnRespuestas = (Button)findViewById(R.id.btnRespuestas);
        btnSalir = (Button)findViewById(R.id.btnSalirAdv);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pagina2 = new Intent(juegoAdivina.this,juegoAdivinaEJ.class);
                pagina2.putExtra("idUser",idUser);
                startActivity(pagina2);
            }
        });

        btnRespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pagina3 = new Intent(juegoAdivina.this,adivinaRespuestas.class);
                startActivity(pagina3);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(juegoAdivina.this,MenuJuegosActivity.class);
                startActivity(menu);
            }
        });
    }
}