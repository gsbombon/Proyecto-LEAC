package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroMedicoActivity extends AppCompatActivity {
    Button btnReg;
    RequestQueue requestQueue;
    EditText etUsuario,etemail,etePassword,eteTelefono,edtRol,eteEspecialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medico);
        etUsuario=(EditText)findViewById(R.id.regUsuarioMed);
        etemail=(EditText)findViewById(R.id.regEmailMed);
        etePassword=(EditText)findViewById(R.id.regPasswordMed);
        eteTelefono=(EditText)findViewById(R.id.regTelefonoMed);
        eteEspecialidad=(EditText)findViewById(R.id.regEspecialidadMed);
        btnReg=(Button)findViewById((R.id.btnRegistroMed));
        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String usuario=etUsuario.getText().toString().trim();
                final String email=etemail.getText().toString().trim();
                final String password=etePassword.getText().toString().trim();
                final String telefono=eteTelefono.getText().toString().trim();
                final String rol="Medico";
                final String especialidad=eteEspecialidad.getText().toString().trim();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.100.118/alzhei_games/registrarMedico.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistroMedicoActivity.this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                        Intent menu= new Intent(getApplicationContext(),MenuRegistroActivity.class);
                        startActivity(menu);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistroMedicoActivity.this, "Usuario no Registrado", Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String>parms=new HashMap<String, String>();
                        parms.put("usuario",usuario);
                        parms.put("email",email);
                        parms.put("password",password);
                        parms.put("telefono",telefono);
                        parms.put("rol",rol);
                        parms.put("especialidad",especialidad);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }
}