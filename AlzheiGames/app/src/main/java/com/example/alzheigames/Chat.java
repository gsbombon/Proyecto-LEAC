package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Chat extends AppCompatActivity {
    EditText etMensaje;
    Button btnEnviar, btnActualizar;
    ListView lvMensajes;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listaMensajes;
    //String URL_MENSAJES = "http://192.168.0.7:8080/alzhei_games/obtenerMensajes.php";
      String URL_MENSAJES = "http://192.168.100.83/alzhei_games/obtenerMensajes.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnActualizar = findViewById(R.id.btnActualizar);
        lvMensajes = findViewById(R.id.lvMensajes);

        obtenerMensajes();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerMensajes();
            }
        });
    }
    public void enviarMensaje() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_MENSAJES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Chat.this, "Se envio exitosamente el mensaje", Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        obtenerMensajes();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(Chat.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("accion", "nuevo");
                parametros.put("mensaje", etMensaje.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void obtenerMensajes() {
        listaMensajes = new ArrayList<String>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_MENSAJES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        // En este apartado se programa lo que deseamos hacer en caso de no haber errores
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("mensajes");
                            for(int i = 0 ; i < jsonArray.length() ; i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);
                                listaMensajes.add(objeto.getString("mensaje"));
                            }
                            adaptador = new ArrayAdapter<String>(Chat.this,android.R.layout.simple_list_item_1, listaMensajes);
                            lvMensajes.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(Chat.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("accion", "");
                parametros.put("mensaje", etMensaje.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}