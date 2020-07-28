package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtPassword;
    Button btnLogin,btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro=findViewById(R.id.btnRegistro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validarUsuario("http://192.168.0.104/alzhei_games/validar_usuario.php");
                validarUsuario("http://192.168.100.118/alzhei_games/validar_usuario.php");
            }
        });
        btnRegistro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        entrarRegistro();
                    }
                }
        );
    }

    private void entrarRegistro(){
        Intent menu= new Intent(getApplicationContext(),MenuRegistroActivity.class);
        startActivity(menu);
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                if (!response.isEmpty()){

                    String cadena[]=response.split(",");
                    //id
                    String idUser=cadena[0];
                    String idResult = idUser.replaceAll("[USUARIO_ID}{:]","");
                    String id = idResult.replace("\"", "");
                    //rol
                    String rolUser=cadena[1];
                    String result = rolUser.replaceAll("[USUARIO_ROL}{:]","");
                    String rol = result.replace("\"", "");

                    Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG).show();
                    if(rol.equals("Paciente")) {
                        Intent paciente = new Intent(getApplicationContext(),PacienteActivity.class);
                        paciente.putExtra("idUser",id);
                        startActivity(paciente);

                    }else if(rol.equals("Medico")){
                        Intent medico = new Intent(getApplicationContext(),MedicoActivity.class);
                        startActivity(medico);
                    }else{
                        Intent cuidador = new Intent(getApplicationContext(),CuidadorActivity.class);
                        startActivity(cuidador);
                       // Toast.makeText(MainActivity.this, rol, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Email or Password Invalid", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR CONEXION DE BASE DE DATOS", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", edtUsuario.getText().toString());
                parametros.put("password", edtPassword.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

