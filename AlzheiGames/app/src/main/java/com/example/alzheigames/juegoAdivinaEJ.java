package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class juegoAdivinaEJ extends AppCompatActivity {

    TextView puntos,contador,vidas,txtCorrecto,txtIncorrecto;
    ImageView imagen;
    EditText textedit;
    Button btnConfirmar;

    int intPunto = 0;
    int intVidas = 3;
    int numGenerado = 0;

    String[] lugares_nombres = {"balcon","baño","cocina","comedor","dormitorio","estudio","garage","jardin","piscina","sala"};
    String[] lugares_imagenes = {"balcon","banho","cocina","comedor","dormitorio","estudio","garage","jardin","piscina","sala"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_adivina_e_j);

        puntos = (TextView)findViewById(R.id.puntos);
        vidas = (TextView)findViewById(R.id.vida);
        contador = (TextView)findViewById(R.id.cuenta);
        imagen = (ImageView)findViewById(R.id.imagen);
        textedit = (EditText)findViewById(R.id.palabra);
        btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        txtIncorrecto = (TextView)findViewById(R.id.txtIncorrecto);
        txtCorrecto = (TextView)findViewById(R.id.txtCorrecto);

        establecerImagen(numGenerado);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String textConfirmar = textedit.getText().toString().toLowerCase();
                if(textConfirmar.equals(lugares_nombres[numGenerado])){
                    txtCorrecto.setVisibility(View.VISIBLE);
                    intPunto+=1;
                    puntos.setText("PUNTOS: "+intPunto);
                    esperar();
                }else{
                    txtIncorrecto.setVisibility(View.VISIBLE);
                    intVidas-=1;
                    vidas.setText("Vidas: "+intVidas);
                }
            }
        });




    }

    public  void esperarIncorrecto(){
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long l) {
                btnConfirmar.setVisibility(View.GONE);
                textedit.setText("");
                textedit.setHint("Ingrese respuesta");
            }
            @Override
            public void onFinish() {
                btnConfirmar.setVisibility(View.VISIBLE);
                txtIncorrecto.setVisibility(View.GONE);
            }
        }.start();
    }

    public  void esperar(){
        new CountDownTimer(4000,1) {
            @Override
            public void onTick(long l) {
                contador.setText(""+ l/1000+1);
                btnConfirmar.setVisibility(View.GONE);
            }

            @Override
            public void onFinish() {
                btnConfirmar.setVisibility(View.VISIBLE);
                numGenerado++;
                contador.setText("");
                establecerImagen(numGenerado);
                txtCorrecto.setVisibility(View.INVISIBLE);
                textedit.setText("");
                textedit.setHint("Ingrese respuesta");
            }
        }.start();
    }

    public void establecerImagen(int numero){
        int id=getResources().getIdentifier(lugares_imagenes[numero],"drawable",getPackageName());
        imagen.setImageResource(id);
    }
}