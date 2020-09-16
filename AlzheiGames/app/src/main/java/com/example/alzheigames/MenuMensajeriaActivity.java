package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class MenuMensajeriaActivity extends AppCompatActivity {
    ListView lvMensajes;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listaMensajes;
    ArrayList<String> listaIdPacientes;
    //String URL_MENSAJES = "http://192.168.0.102/alzhei_games/obtenerPacinetesCuidador.php";
    String URL_MENSAJES = "http://192.168.100.83/alzhei_games/obtenerPacinetesCuidador.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mensajeria);
        lvMensajes = findViewById(R.id.lvMensajesPac);
        obtenerPacientes();

        lvMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String idCui=getIntent().getStringExtra("idUser");
                String idPac= (String) listaIdPacientes.get(i);
                Toast.makeText(MenuMensajeriaActivity.this,idPac, Toast.LENGTH_LONG).show();
                Intent tele = new Intent(MenuMensajeriaActivity.this,ChatCuidadorActivity.class);
                tele.putExtra("idUser",idPac);
                tele.putExtra("idCui",idCui);
                startActivity(tele);
            }
        });

    }
    public void obtenerPacientes() {
        listaMensajes = new ArrayList<String>();
        listaIdPacientes = new ArrayList<String>();
        final String idCui=getIntent().getStringExtra("idUser");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_MENSAJES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("mensajes");
                            for(int i = 0 ; i < jsonArray.length() ; i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);
                                listaMensajes.add(objeto.getString("USUARIO_NOMBRE"));
                                listaIdPacientes.add(objeto.getString("USUARIO_ID"));

                            }
                            adaptador = new ArrayAdapter<String>(MenuMensajeriaActivity.this,android.R.layout.simple_list_item_1, listaMensajes);
                            lvMensajes.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // En caso de tener algun error en la obtencion de los datos
                Toast.makeText(MenuMensajeriaActivity.this, "ERROR EN LA CONEXIÓN", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("accion", "");
                parametros.put("idCui",idCui);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}