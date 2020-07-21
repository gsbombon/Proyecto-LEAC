package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicoActivity extends AppCompatActivity {

    Button verResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);

        verResultado = (Button)findViewById(R.id.btnResultado);
        verResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verResultado = new Intent(MedicoActivity.this, medico_verResultado.class);
                startActivity(verResultado);
            }
        });
    }
}