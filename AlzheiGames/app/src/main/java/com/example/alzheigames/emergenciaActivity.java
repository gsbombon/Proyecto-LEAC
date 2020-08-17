package com.example.alzheigames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class emergenciaActivity extends AppCompatActivity {
    private  Button enviarTele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);
        enviarTele = (Button)findViewById(R.id.btnSolicitarAyuda);
        if(ActivityCompat.checkSelfPermission(emergenciaActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(emergenciaActivity.this,Manifest
                .permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){ActivityCompat.requestPermissions(emergenciaActivity.this,new String[]
                { Manifest.permission.SEND_SMS,},1000);
        }else{

        };
        recuperarTelefono("http://192.168.100.83/alzhei_games/obtener_Telefono.php");
    }

    private void recuperarTelefono(String URL){
        final String idUser=getIntent().getStringExtra("idUser");;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                if (!response.isEmpty()){
                    try {

                        String tele = response.replaceAll("[USUARIO_TELEFONO}{:]", "");
                        String telefono = tele.replace("\"", "");

                        String extencion="+593";
                        final String numTelefono=extencion.concat(telefono);
                        Toast.makeText(emergenciaActivity.this,numTelefono, Toast.LENGTH_LONG).show();
                        enviarTele.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                enviarMensaje(numTelefono,"Hola, Necesito ayuda de inmediato porfavor");
                            }
                        });


                    }catch(Exception ex){
                        Toast.makeText(emergenciaActivity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(emergenciaActivity.this, "ERROR CONEXION DE BASE DE DATOS", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("idPaciente", idUser);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            AlertDialog.Builder alertDialogBulider=new AlertDialog.Builder(emergenciaActivity.this);
            alertDialogBulider
                    .setMessage("Su solicitud de ayuda ha sido Enviada a su cuidador\n")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog alertDialog=alertDialogBulider.create();
            alertDialog.show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}