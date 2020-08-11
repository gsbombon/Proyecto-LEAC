package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        enviarTele.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enviarMensaje("+59398862983","Hola Necisto ayuda");
            }
        });
    }
    private void enviarMensaje (String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}