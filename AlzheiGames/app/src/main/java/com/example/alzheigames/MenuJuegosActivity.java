package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJuegosActivity extends AppCompatActivity {

    Button irAdivina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juegos);

        irAdivina = (Button)findViewById(R.id.btnJugarAdivina);
        irAdivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irJugarAdivina = new Intent(MenuJuegosActivity.this, juegoAdivina.class);
                startActivity(irJugarAdivina);
            }
        });
    }


}