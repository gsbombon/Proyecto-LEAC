package com.example.alzheigames;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class medico_verResultado extends AppCompatActivity {

    Spinner spinnerPac;
    ArrayList<String> pacientes;
    TextView nomPaciente;
    ArrayAdapter<String> adapater;
   // String[] horario = {"Matutino","Vespertino","Nocturno"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_ver_resultado);
        spinnerPac = (Spinner) findViewById(R.id.spinnerPPP);
        pacientes = new ArrayList<>();

        listarPPP();

        //adapater = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pacientes);
        //spinnerPac.setAdapter(adapater);

        spinnerPac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String nombre = spinnerPac.getSelectedItem().toString().trim();
                nomPaciente = (TextView)findViewById(R.id.txtNomPaciente);
                nomPaciente.setText(""+nombre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void listarPPP(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://192.168.0.102/alzhei_games/obtenerPaciente.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("usuario");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("USUARIO_NOMBRE");
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




}