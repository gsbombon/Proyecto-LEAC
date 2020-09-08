package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuegoMemoriaActivity extends AppCompatActivity {

    Button b_new;
    Button button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9,
            button10, button11, button12;

    List<Integer> buttons;

    int curLevel, curGuess, intPunto;

    String idJUEGO = "3";

    String URL = "http://192.168.100.83/alzhei_games/registrarJuegoMemoria.php";
    //String URL = "http://192.168.0.4:8080/alzhei_games/registrarJuegoMemoria.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_memoria);

        b_new = (Button) findViewById(R.id.b_new);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);

        button1.setTag(1);
        button2.setTag(2);
        button3.setTag(3);
        button4.setTag(4);
        button5.setTag(5);
        button6.setTag(6);
        button7.setTag(7);
        button8.setTag(8);
        button9.setTag(9);
        button10.setTag(10);
        button11.setTag(11);
        button12.setTag(12);

        disableButtons();

        buttons = new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);
        buttons.add(4);
        buttons.add(5);
        buttons.add(6);
        buttons.add(7);
        buttons.add(8);
        buttons.add(9);
        buttons.add(10);
        buttons.add(11);
        buttons.add(12);

        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_new.setVisibility(View.INVISIBLE);
                curGuess = 0;
                curLevel = 1;
                generateButtons(curLevel);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("O\nOOO\nO");
            }
        });
    }

    private void buttonLogic(View view){
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < curLevel; i++){
            tempList.add(buttons.get(i));
        }

        if (tempList.contains(view.getTag())){
            curGuess++;
            checkWin();
        } else {
            lostGame();
        }
    }

    private  void checkWin(){
        if (curGuess == curLevel){
            disableButtons();
            if (curLevel == 12){
                intPunto=12;
                Toast.makeText(this, "Ganaste! "+curLevel, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Puntaje: "+intPunto, Toast.LENGTH_SHORT).show();
                agregarPuntajeDB();
                b_new.setVisibility(View.VISIBLE);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curGuess = 0;
                        curLevel++;
                        intPunto=curLevel-1;
                        generateButtons(curLevel);
                    }
                }, 1000);
            }
        }
    }

    private void  lostGame(){
        Toast.makeText(this, "Fallaste! ", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Puntaje: "+intPunto, Toast.LENGTH_SHORT).show();
        agregarPuntajeDB();
        disableButtons();
        b_new.setVisibility(View.VISIBLE);
    }

    private void generateButtons(int number){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");

        Collections.shuffle(buttons);

        for (int i = 0; i < number; i++){
            showButton(buttons.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");
                button10.setText("");
                button11.setText("");
                button12.setText("");

                enableButtons();
            }
        }, 1000);
    }

    private void showButton(int number){
        switch (number){
            case 1:
                button1.setText("O\nOOO\nO");
                break;
            case 2:
                button2.setText("O\nOOO\nO");
                break;
            case 3:
                button3.setText("O\nOOO\nO");
                break;
            case 4:
                button4.setText("O\nOOO\nO");
                break;
            case 5:
                button5.setText("O\nOOO\nO");
                break;
            case 6:
                button6.setText("O\nOOO\nO");
                break;
            case 7:
                button7.setText("O\nOOO\nO");
                break;
            case 8:
                button8.setText("O\nOOO\nO");
                break;
            case 9:
                button9.setText("O\nOOO\nO");
                break;
            case 10:
                button10.setText("O\nOOO\nO");
                break;
            case 11:
                button11.setText("O\nOOO\nO");
                break;
            case 12:
                button12.setText("O\nOOO\nO");
                break;
        }
    }

    private void enableButtons(){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button10.setEnabled(true);
        button11.setEnabled(true);
        button12.setEnabled(true);
    }

    private void disableButtons(){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        button12.setEnabled(false);
    }

    public void agregarPuntajeDB(){
        final String puntos= String.valueOf(intPunto);
        final String idUser=getIntent().getStringExtra("idUser");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(JuegoMemoriaActivity.this, "Guardado", Toast.LENGTH_LONG).show();
                //Intent menu= new Intent(getApplicationContext(),JuegoMemoriaActivity.class);
                //startActivity(menu);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JuegoMemoriaActivity.this, "NO REGISTRADO EN DB", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> parms=new HashMap<String, String>();
                //DATOS A ENVIAR
                parms.put("idUser",idUser);
                parms.put("puntuacion",puntos);
                parms.put("idJuego",idJUEGO);
                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}