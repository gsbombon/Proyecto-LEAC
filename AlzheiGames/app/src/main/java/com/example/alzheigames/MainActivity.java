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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                edtUsuario.setError(null);
                String email = edtUsuario.getText().toString();
                String password = edtPassword.getText().toString();
                if("".equals(email)){
                    edtUsuario.setError("Introduce un correo");
                    edtUsuario.requestFocus();
                    return;
                }else if("".equals(password)){
                    edtPassword.setError("Introduce tu contraseña ");
                    edtPassword.requestFocus();
                }else if("".equals(password) &&  "".equals(email)){
                    edtUsuario.setError("Introduce un correo");
                    edtUsuario.requestFocus();
                    edtPassword.setError("Introduce tu clave ");
                    edtPassword.requestFocus();
                }else {
                    if(validarCorreo(email)) {
                        validarUsuario("http://192.168.0.102/alzhei_games/validar_usuario.php");
                        //validarUsuario("http://192.168.100.118/alzhei_games/validar_usuario.php");
                    }else{
                        edtUsuario.setError("Introduce un correo valido");
                        edtUsuario.requestFocus();
                    }
                }
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
                    try {
                        String cadena[] = response.split(",");
                        //id
                        String idUser = cadena[0];
                        String idResult = idUser.replaceAll("[USUARIO_ID}{:]", "");
                        String id = idResult.replace("\"", "");
                        //rold
                        String rolUser = cadena[1];
                        String result = rolUser.replaceAll("[USUARIO_ROL}{:]", "");
                        String rol = result.replace("\"", "");

                        Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG).show();
                        if (rol.equals("Paciente")) {
                            Intent paciente = new Intent(getApplicationContext(), PacienteActivity.class);
                            paciente.putExtra("idUser", id);
                            startActivity(paciente);

                        } else if (rol.equals("Medico")) {
                            Intent medico = new Intent(getApplicationContext(), MedicoActivity.class);
                            medico.putExtra("idUser", id);
                            startActivity(medico);
                        } else if (rol.equals("Cuidador")) {
                            Intent cuidador = new Intent(getApplicationContext(), CuidadorActivity.class);
                            cuidador.putExtra("idUser", id);
                            startActivity(cuidador);
                            // Toast.makeText(MainActivity.this, rol, Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception ex){
                        Toast.makeText(MainActivity.this, "Correo o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Correo o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
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

