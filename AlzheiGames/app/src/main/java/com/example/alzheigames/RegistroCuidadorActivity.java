package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroCuidadorActivity extends AppCompatActivity {

    Spinner  sHorario ;
    Button btnReg;
    RequestQueue requestQueue;
    EditText etUsuario,etemail,etePassword,eteTelefono,edtRol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuidador);
        etUsuario=(EditText)findViewById(R.id.regUsuarioCui);
        etemail=(EditText)findViewById(R.id.regEmailCui);
        etePassword=(EditText)findViewById(R.id.regPasswordCui);
        eteTelefono=(EditText)findViewById(R.id.regTelefonoCui);
        btnReg=(Button)findViewById((R.id.btnRegistroCui));
        eteTelefono.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        String[] horario = {"Matutino","Vespertino","Nocturno"};
        sHorario = (Spinner) findViewById(R.id.horarioRegCui);
        sHorario.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, horario));
        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etUsuario.setError(null);
                String usuario = etUsuario.getText().toString();
                String email= etemail.getText().toString();
                String password = etePassword.getText().toString();
                String telefono = eteTelefono.getText().toString();

                if ("".equals(usuario)) {
                    etUsuario.setError("Ingrese un usuario");
                    etUsuario.requestFocus();
                    return;
                }else if("".equals(email)){
                    etemail.setError("Ingrese un correo");
                    etemail.requestFocus();
                }else if("".equals(password)){
                    etePassword.setError("Ingrese una contraseña");
                    etePassword.requestFocus();
                }else if("".equals(telefono)){
                    eteTelefono.setError("Ingrese un telefono celular");
                    eteTelefono.requestFocus();
                }else{
                    if(!validarCorreo(email)) {
                        etemail.setError("Introduce un correo valido");
                        etemail.requestFocus();
                    } else{
                        //registroCuidador("http://192.168.0.102/alzhei_games/registrarCuidador.php");
                        registroCuidador("http://192.168.100.83/alzhei_games/registrarCuidador.php");
                    }
                }


            }
        });
    }
    private void registroCuidador(String URL){
        final String usuario=etUsuario.getText().toString().trim();
        final String email=etemail.getText().toString().trim();
        final String password=etePassword.getText().toString().trim();
        final String telefono=eteTelefono.getText().toString().trim();
        final String rol="Cuidador";
        final String horario=sHorario.getSelectedItem().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegistroCuidadorActivity.this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                Intent menu= new Intent(getApplicationContext(),MenuRegistroActivity.class);
                startActivity(menu);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroCuidadorActivity.this, "Usuario no Registrado", Toast.LENGTH_LONG).show();
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
                parms.put("horario",horario);
                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    private boolean validarCorreo(String txtEmail){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(txtEmail);

        if (mather.find() == true) {
            return  true;
        } else {
            return false;
        }
    }
}