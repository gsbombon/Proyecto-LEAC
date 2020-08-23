package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CuidadorActivity extends AppCompatActivity {

    Button verResultado,btnTeleasistencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidador);
        btnTeleasistencia = (Button)findViewById(R.id.btnTeleasistencia);

        btnTeleasistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tele = new Intent(CuidadorActivity.this,MenuMensajeriaActivity.class);
                String idUser=getIntent().getStringExtra("idUser");
                tele.putExtra("idUser",idUser);
                startActivity(tele);
            }
        });
    }
}