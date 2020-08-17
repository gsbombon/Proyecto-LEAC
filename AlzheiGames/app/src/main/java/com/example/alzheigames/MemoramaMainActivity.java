package com.example.alzheigames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemoramaMainActivity extends AppCompatActivity {
    TextView tv_p1,tv_p2;
    ImageView iv_11,iv_12,iv_13,iv_14,iv_21,iv_22,iv_23,iv_24,iv_31,iv_32,iv_33,iv_34;
    Integer[] cardsArray={101,102,103,104,105,106,201,202,203,204,205,206};
    int img101,img102,img103,img104,img105,img106,
            img201,img202,img203,img204,img205,img206;
    int firstCard,secondCard;
    int clickedFirst,clickedSecond;
    int cardNumber=1;

    int turn=1;
    int playerPoint=0,cpuPoints=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama_main);
        tv_p1=(TextView)findViewById(R.id.tv_p1);
        tv_p2=(TextView)findViewById(R.id.tv_p2);

        iv_11=(ImageView)findViewById(R.id.iv_11);
        iv_12=(ImageView)findViewById(R.id.iv_12);
        iv_13=(ImageView)findViewById(R.id.iv_13);
        iv_14=(ImageView)findViewById(R.id.iv_14);
        iv_21=(ImageView)findViewById(R.id.iv_21);
        iv_22=(ImageView)findViewById(R.id.iv_22);
        iv_23=(ImageView)findViewById(R.id.iv_23);
        iv_24=(ImageView)findViewById(R.id.iv_24);
        iv_31=(ImageView)findViewById(R.id.iv_31);
        iv_32=(ImageView)findViewById(R.id.iv_32);
        iv_33=(ImageView)findViewById(R.id.iv_33);
        iv_34=(ImageView)findViewById(R.id.iv_34);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");

        frontOfCardsRes();

        Collections.shuffle(Arrays.asList(cardsArray));
        tv_p2.setTextColor(Color.BLACK);
        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_11,theCard);
            }
        });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_12,theCard);

            }
        });
        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_13,theCard);

            }
        });
        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_14,theCard);

            }
        });
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_21,theCard);

            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_22,theCard);

            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_23,theCard);

            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_24,theCard);

            }
        });
        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_31,theCard);

            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_32,theCard);

            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_33,theCard);

            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard=Integer.parseInt((String)view.getTag());
                doStuff(iv_34,theCard);

            }
        });


    }

    private  void doStuff(ImageView iv,int card){
        if(cardsArray[card]==101){
            iv.setImageResource(img101);
        }else if(cardsArray[card]==102){
            iv.setImageResource(img102);
        }else if(cardsArray[card]==103){
            iv.setImageResource(img103);
        }else if(cardsArray[card]==104){
            iv.setImageResource(img104);
        }else if(cardsArray[card]==105){
            iv.setImageResource(img105);
        }else if(cardsArray[card]==106){
            iv.setImageResource(img106);
        }else if(cardsArray[card]==201){
            iv.setImageResource(img201);
        }else if(cardsArray[card]==202){
            iv.setImageResource(img202);
        }else if(cardsArray[card]==203){
            iv.setImageResource(img203);
        }else if(cardsArray[card]==204){
            iv.setImageResource(img204);
        }else if(cardsArray[card]==205){
            iv.setImageResource(img205);
        }else if(cardsArray[card]==206){
            iv.setImageResource(img206);
        }


        if(cardNumber==1){
            firstCard=cardsArray[card];
            if(firstCard>200){
                firstCard=firstCard-100;
            }
            cardNumber=2;
            clickedFirst=card;
            iv.setEnabled(false);

        }else if(cardNumber==2){
            secondCard=cardsArray[card];
            if(secondCard>200){
                secondCard=secondCard-100;
            }
            cardNumber=1;
            clickedSecond=card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            },1000);

        }

    }
    private void calculate(){
        if(firstCard==secondCard){
            if(clickedFirst==0){
                iv_11.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if(clickedFirst==11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond==0){
                iv_11.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            if(turn==1){
                turn=1;
                playerPoint++;
                tv_p1.setText("Aciertos: "+playerPoint);
            }
        }else{
            iv_11.setImageResource(R.drawable.interro);
            iv_12.setImageResource(R.drawable.interro);
            iv_13.setImageResource(R.drawable.interro);
            iv_14.setImageResource(R.drawable.interro);
            iv_21.setImageResource(R.drawable.interro);
            iv_22.setImageResource(R.drawable.interro);
            iv_23.setImageResource(R.drawable.interro);
            iv_24.setImageResource(R.drawable.interro);
            iv_31.setImageResource(R.drawable.interro);
            iv_32.setImageResource(R.drawable.interro);
            iv_33.setImageResource(R.drawable.interro);
            iv_34.setImageResource(R.drawable.interro);

            if(turn==1){
                turn=1;
                cpuPoints++;
                tv_p2.setText("Desaciertos: "+cpuPoints);
            }

        }
        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        checkend();

    }
    private void checkend(){
        if(iv_11.getVisibility()==View.INVISIBLE &&
                iv_11.getVisibility()==View.INVISIBLE &&
                iv_12.getVisibility()==View.INVISIBLE &&
                iv_13.getVisibility()==View.INVISIBLE &&
                iv_14.getVisibility()==View.INVISIBLE &&
                iv_21.getVisibility()==View.INVISIBLE &&
                iv_22.getVisibility()==View.INVISIBLE &&
                iv_23.getVisibility()==View.INVISIBLE &&
                iv_24.getVisibility()==View.INVISIBLE &&
                iv_31.getVisibility()==View.INVISIBLE &&
                iv_32.getVisibility()==View.INVISIBLE &&
                iv_33.getVisibility()==View.INVISIBLE &&
                iv_34.getVisibility()==View.INVISIBLE){
            double acierto=Double.parseDouble(String.valueOf(playerPoint));
            double desacierto=Double.parseDouble(String.valueOf(cpuPoints));
            double puntTotal=acierto/(acierto+desacierto);
            puntTotal=puntTotal*100;
            final String puntJuego=String.valueOf(puntTotal);
            final String idUser=getIntent().getStringExtra("idUser");

           StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.0.102/alzhei_games/registrarMemorama.php", new Response.Listener<String>() {
           // StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.100.83/alzhei_games/registrarMemorama.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Toast.makeText(MemoramaMainActivity.this, "Guarado", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(MemoramaMainActivity.this, "No Guardado", Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams()  {
                    Map<String,String>parms=new HashMap<String, String>();
                    parms.put("idPaciente",idUser);
                    parms.put("idJuego","1");
                    parms.put("puntuacion",puntJuego);

                    return parms;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);



            AlertDialog.Builder alertDialogBulider=new AlertDialog.Builder(MemoramaMainActivity.this);
            alertDialogBulider
                    .setMessage("JUEGO TERMINADO! \nAciertos: "+playerPoint+"\nDesaciertos: "+cpuPoints)
                    .setCancelable(false)
                    .setPositiveButton("NUEVO JUEGO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent =new Intent(getApplicationContext(),MemoramaMainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog=alertDialogBulider.create();
            alertDialog.show();
        }
    }


    private void frontOfCardsRes(){
        img101=R.drawable.img101;
        img102=R.drawable.img102;
        img103=R.drawable.img103;
        img104=R.drawable.img104;
        img105=R.drawable.img105;
        img106=R.drawable.img106;
        img201=R.drawable.img201;
        img202=R.drawable.img202;
        img203=R.drawable.img203;
        img204=R.drawable.img204;
        img205 =R.drawable.img205;
        img206=R.drawable.img206;

    }
}