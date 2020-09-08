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

public class RegistroMedicoActivity extends AppCompatActivity {
    Button btnReg;
    RequestQueue requestQueue;
    Spinner sEspecialidad;
    EditText etUsuario,etemail,etePassword,eteTelefono,edtRol,eteEspecialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medico);
        etUsuario=(EditText)findViewById(R.id.regUsuarioMed);
        etemail=(EditText)findViewById(R.id.regEmailMed);
        etePassword=(EditText)findViewById(R.id.regPasswordMed);
        eteTelefono=(EditText)findViewById(R.id.regTelefonoMed);
        eteTelefono.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        String[] especialidad = {
        "Alergología", "Anestesiología",
        "Angiología", "Cardiología",
        "Endocrinología", "Epidemiología",
        "Gastroenterología", "Geriatría",
        "Hematología ","Hepatología",
        "Infectología" ,"Medicina aeroespacial",
        "Medicina del deporte", "Medicina de emergencia",
        "Medicina familiar y comunitaria",
        "Medicina física y rehabilitación",
        "Medicina forense",
        "Medicina intensiva",
        "Medicina interna",
        "Medicina preventiva y salud pública",
        "Medicina del trabajo", "Nefrología",
        "Neumología","Neurología",
        "Nutriología","Oncología médica",
        "Oncología radioterápica",
        "Pediatría", "Psiquiatría",
        "Reumatología","Toxicología","Traumatología"};
        sEspecialidad = (Spinner) findViewById(R.id.regEspecialidadMed);
        sEspecialidad.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,especialidad));
        btnReg=(Button)findViewById((R.id.btnRegistroMed));
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
                        //registroMedico("http://192.168.0.102/alzhei_games/registrarCuidador.php");
                        registroMedico("http://192.168.100.83/alzhei_games/registrarCuidador.php");
                    }
                }

            }
        });

    }
    private void registroMedico(String URL){

        final String usuario=etUsuario.getText().toString().trim();
        final String email=etemail.getText().toString().trim();
        final String password=etePassword.getText().toString().trim();
        final String telefono=eteTelefono.getText().toString().trim();
        final String rol="Medico";
        final String especialidad=sEspecialidad.getSelectedItem().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL , new Response.Listener<String>() {
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