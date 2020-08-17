package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class medico_verResultado extends AppCompatActivity {
    Spinner spinnerPac;
    Spinner spinnerJuegos;
    ArrayList<String> pacientes;
    ArrayList<String> juegos;
    TextView nomPaciente, txtPuntos;
    Button btnDetalle;
    ArrayAdapter<String> adapater;
    List<Resultado> listaResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_ver_resultado);
        spinnerPac = (Spinner) findViewById(R.id.spinnerPPP);
        spinnerJuegos = (Spinner) findViewById(R.id.spinnerJuegos);
        pacientes = new ArrayList<>();
        juegos = new ArrayList<>();
        btnDetalle = (Button)findViewById(R.id.btnDetalle);
        String idUser = getIntent().getStringExtra("idUser");
        listarPPP(idUser);
        spinnerPac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                juegos.clear();
                final String nombre = spinnerPac.getSelectedItem().toString().trim();
                nomPaciente = (TextView) findViewById(R.id.txtNomPaciente);
                nomPaciente.setText("" + nombre);
                String nombrePaciente = nomPaciente.getText().toString();
                //String dirPuntaje = "http://192.168.0.104/alzhei_games/puntosPaciente.php?nombreAS="+nombrePaciente;
                String dirPuntaje = "http://192.168.100.83/alzhei_games/puntosPaciente.php?nombreAS=" + nombrePaciente;
                obtenPuntaje(dirPuntaje);
                listarJuegos(nombrePaciente);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombreJuegoSEC = spinnerJuegos.getSelectedItem().toString().trim();
                String nombrePacienteSEC = nomPaciente.getText().toString();
                Intent sig = new Intent(medico_verResultado.this, DetalleJuego.class);
                sig.putExtra("nombreJuego", nombreJuegoSEC);
                sig.putExtra("nombrePaciente", nombrePacienteSEC);
                startActivity(sig);
            }
        });

    }

    public void listarJuegos(String nomPaciente) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL_JUEGOS = "http://192.168.100.83/alzhei_games/obtenerListaJuegos.php?nomPaciente=" + nomPaciente;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_JUEGOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("JUEGO_NOMBRE");
                                juegos.add(country);
                            }
                            spinnerJuegos.setAdapter(new ArrayAdapter<String>(medico_verResultado.this, android.R.layout.simple_spinner_dropdown_item, juegos));
                        } catch (JSONException e) {
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

    public void listarPPP(String idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL_PACIENTES = "http://192.168.100.83/alzhei_games/obtenerPaciente.php?id=" + idUser;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PACIENTES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("USUARIO_NOMBRE");
                                pacientes.add(country);
                            }
                            spinnerPac.setAdapter(new ArrayAdapter<String>(medico_verResultado.this, android.R.layout.simple_spinner_dropdown_item, pacientes));
                        } catch (JSONException e) {
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

    public void obtenPuntaje(String URL) {
        txtPuntos = (TextView) findViewById(R.id.textPuntaje);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtPuntos.setText(jsonObject.getString("SUMA"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
