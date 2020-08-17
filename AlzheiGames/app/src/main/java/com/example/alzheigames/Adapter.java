package com.example.alzheigames;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ResultadoViewHolder> {

    private List<Resultado> resultadoList;

    public static class ResultadoViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewNomJuego,txtViewPuntuacion,txtViewFecha;

        public ResultadoViewHolder(View itemView) {
            super(itemView);
            txtViewNomJuego = (TextView) itemView.findViewById(R.id.txtNomJuego);
            txtViewPuntuacion = (TextView)itemView.findViewById(R.id.txtPuntuacion);
            txtViewFecha = (TextView)itemView.findViewById(R.id.txtFecha);
        }
    }


    public Adapter(List<Resultado> resultadoList) {
        this.resultadoList=resultadoList;
    }

    @Override
    public ResultadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        ResultadoViewHolder holder = new ResultadoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ResultadoViewHolder holder, int position) {
        holder.txtViewNomJuego.setText(resultadoList.get(position).getJug_nombre());
        holder.txtViewPuntuacion.setText("Resultado : "+resultadoList.get(position).getJug_puntuacion()+" /100");
        holder.txtViewFecha.setText("Fecha: "+resultadoList.get(position).getJug_fecha());
    }

    @Override
    public int getItemCount() {
        return resultadoList.size();
    }
}
