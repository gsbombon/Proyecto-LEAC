package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleJuego extends AppCompatActivity {

    TextView txtNombreJuego,txtPuntajeJuego,txtNombrePaciente;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);
        rv = (RecyclerView)findViewById(R.id.recyclerDetalles);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        txtNombreJuego = (TextView)findViewById(R.id.txtJuego);
        txtNombrePaciente = (TextView)findViewById(R.id.txtNombrePPP);
        String nombrePaciente = getIntent().getStringExtra("nombrePaciente");
        String nombreJuego = getIntent().getStringExtra("nombreJuego");

        txtNombreJuego.setText(""+nombreJuego);
        txtNombrePaciente.setText(""+nombrePaciente);

        String dirPuntaje = "http://192.168.100.83/alzhei_games/puntosPacienteJuego.php?nombreAS="+nombrePaciente+"&nomJuego="+nombreJuego;
        obtenPuntaje(dirPuntaje);
        String dirLista = "http://192.168.100.83/alzhei_games/tut35/consultarReserva.php?nombreAS="+nombrePaciente+"&nomJuego="+nombreJuego;
        recibirDatos(dirLista);
    }

    public void obtenPuntaje(String URL){
        txtPuntajeJuego = (TextView) findViewById(R.id.textPuntaje123);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtPuntajeJuego.setText("Suma de Puntos: "+jsonObject.getString("SUMA"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    //LIST VIEW
    public  void recibirDatos(String URL){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = response.replace("][", ",");
                if (response.length() > 0) {
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson", "" + ja.length());
                        cargarRecycler(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error",""+error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    public void cargarRecycler(JSONArray ja){
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<Resultado> listaResult = new ArrayList<>();

        for(int i=0;i<ja.length();i+=3){
            try {
                Resultado resultado1 = new Resultado(ja.getString(i),ja.getString(i+1),ja.getString(i+2));
                listaResult.add(resultado1);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        Adapter adapterRV = new Adapter(listaResult);
        rv.setAdapter(adapterRV);
    }
}