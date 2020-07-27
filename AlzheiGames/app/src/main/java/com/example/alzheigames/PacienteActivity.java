package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class PacienteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
    }

    public void juegos_btn(View view){
        Intent i = new Intent(this,MenuJuegosActivity.class);
        String idUser=getIntent().getStringExtra("idUser");
        i.putExtra("idUser",idUser);
        startActivity(i);
    }

    public void teleasistencia_btn(View view){
        Intent i = new Intent(this,AsistenciaActivity.class);
        startActivity(i);
    }
}