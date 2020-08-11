package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
    ArrayList<String> pacientes;
    TextView nomPaciente,txtPuntos,txtprueba;
    ArrayAdapter<String> adapater;
    List<Resultado> listaResultados;
    RecyclerView rv;
    ListView listaResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_ver_resultado);
        spinnerPac = (Spinner) findViewById(R.id.spinnerPPP);
        pacientes = new ArrayList<>();
        txtprueba = (TextView)findViewById(R.id.textPrueba);
        listaResult = (ListView)findViewById(R.id.listaResultados);
        rv = (RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        listarPPP();
        spinnerPac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String nombre = spinnerPac.getSelectedItem().toString().trim();
                nomPaciente = (TextView)findViewById(R.id.txtNomPaciente);
                nomPaciente.setText(""+nombre);
                String nombrePaciente= nomPaciente.getText().toString();
                String dirPuntaje = "http://192.168.0.104/alzhei_games/puntosPaciente.php?nombreAS="+nombrePaciente;
                //String dirPuntaje = "http://192.168.100.118/alzhei_games/puntosPaciente.php?nombreAS="+nombrePaciente;
                obtenPuntaje(dirPuntaje);
               String dirLista = "http://192.168.0.104/alzhei_games/tut35/consultarReserva.php?nombreAS="+nombrePaciente;
                //String dirLista = "http://192.168.100.118/alzhei_games/tut35/consultarReserva.php?nombreAS="+nombrePaciente;
                recibirDatos(dirLista);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void listarPPP(){
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        //StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://192.168.0.104/alzhei_games/obtenerPaciente.php",
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://192.168.100.83/alzhei_games/obtenerPaciente.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("usuario");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String country=jsonObject1.getString("USUARIO_NOMBRE");
                                pacientes.add(country);
                            }
                            spinnerPac.setAdapter(new ArrayAdapter<String>(medico_verResultado.this, android.R.layout.simple_spinner_dropdown_item, pacientes));
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

    public void obtenPuntaje(String URL){
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
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    //LIST VIEW
    public  void recibirDatos(String URL){
        Toast.makeText(getApplicationContext(),URL,Toast.LENGTH_SHORT).show();

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

    public void cargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();

        for(int i=0;i<ja.length();i+=3){
            try {
                lista.add("JUEGO: "+ja.getString(i)+"\n Puntuacion: "+ja.getString(i+1)+"\n Fecha: "+ja.getString(i+2));
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
                listaResult.setAdapter(adaptador);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

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