package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuRegistroActivity extends AppCompatActivity {
Button btnPacienteReg,btnMedicoReg,btnCuidadorReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registro);
        btnPacienteReg=findViewById(R.id.opcPaciente);
        btnMedicoReg=findViewById(R.id.opcMedico);
        btnCuidadorReg=findViewById(R.id.opcCuidador);

        btnPacienteReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu= new Intent(getApplicationContext(),RegistroPacienteActivity.class);
                startActivity(menu);

            }
        });
        btnMedicoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu= new Intent(getApplicationContext(),RegistroMedicoActivity.class);
                startActivity(menu);

            }
        });
        btnCuidadorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu= new Intent(getApplicationContext(),RegistroCuidadorActivity.class);
                startActivity(menu);

            }
        });
    }
}