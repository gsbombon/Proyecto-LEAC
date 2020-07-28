package com.example.alzheigames;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistroPacienteActivity extends AppCompatActivity {
    Spinner spinner ;
    Spinner spinnerMedico ;
    ArrayList<String> cuidador,medico;
    Button btnReg;
    Spinner sGrado;
    RequestQueue requestQueue;

    EditText etUsuario,etemail,etePassword,eteTelefono,edtRol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);

        cuidador = new ArrayList<>();
        medico=new ArrayList<>();
        spinner= (Spinner) findViewById(R.id.spinnerCuiador);
        spinnerMedico= (Spinner) findViewById(R.id.spinnerMedico);
        listarCuidador();
        listarMedico();
        //datos del formulario
        etUsuario=(EditText)findViewById(R.id.regUsuario);
        etemail=(EditText)findViewById(R.id.regEmail);
        etePassword=(EditText)findViewById(R.id.regPassword);
        eteTelefono=(EditText)findViewById(R.id.regTelefono);
        btnReg=(Button)findViewById((R.id.btnRegistroPac));
        String[] Grado = {"Bajo","Medio"};
        sGrado = (Spinner) findViewById(R.id.spinnerGrado);
        sGrado.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Grado));
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usuario=etUsuario.getText().toString().trim();
                final String email=etemail.getText().toString().trim();
                final String password=etePassword.getText().toString().trim();
                final String telefono=eteTelefono.getText().toString().trim();
                final String rol="Paciente";
                final String cuidador=spinner.getSelectedItem().toString().trim();
                final String medico=spinnerMedico.getSelectedItem().toString().trim();
                final String grado=sGrado.getSelectedItem().toString().trim();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.0.104/alzhei_games/registrarPaciente.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistroPacienteActivity.this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                        Intent menu= new Intent(getApplicationContext(),MenuRegistroActivity.class);
                        startActivity(menu);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistroPacienteActivity.this, "Usuario no Registrado", Toast.LENGTH_LONG).show();

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
                        parms.put("medico",medico);
                        parms.put("cuidador",cuidador);
                        parms.put("grado",grado);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }
    public void listarCuidador(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://192.168.0.104/alzhei_games/obtener.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("usuario");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String country=jsonObject1.getString("USUARIO_NOMBRE");
                                cuidador.add(country);
                            }
                            spinner.setAdapter(new ArrayAdapter<String>(RegistroPacienteActivity.this, android.R.layout.simple_spinner_dropdown_item, cuidador));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void listarMedico(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://192.168.0.104/alzhei_games/obtenerMedico.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("usuario");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String country=jsonObject1.getString("USUARIO_NOMBRE");
                                medico.add(country);
                            }
                            spinnerMedico.setAdapter(new ArrayAdapter<String>(RegistroPacienteActivity.this, android.R.layout.simple_spinner_dropdown_item, medico));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }



}