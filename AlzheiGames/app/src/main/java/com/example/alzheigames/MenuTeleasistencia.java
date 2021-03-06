package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuTeleasistencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_teleasistencia);
    }
    public void emerge_btn(View view){
        Intent i = new Intent(this,emergenciaActivity.class);
        String idUser = getIntent().getStringExtra("idUser");
        i.putExtra("idUser",idUser);
        startActivity(i);
    }
    public void btn_salirTele(View view){
        Intent i = new Intent(this,PacienteActivity.class);
        startActivity(i);
    }
    public void btn_mensajeria(View view){
        Intent i = new Intent(this,Chat.class);
        String idUser = getIntent().getStringExtra("idUser");
        i.putExtra("idUser",idUser);
        startActivity(i);
    }
}