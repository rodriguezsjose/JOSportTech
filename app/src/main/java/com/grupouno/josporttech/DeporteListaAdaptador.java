package com.grupouno.josporttech;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupouno.josporttech.entidad.Deporte;

import java.util.ArrayList;
import java.util.List;

public class DeporteListaAdaptador extends RecyclerView.Adapter<DeporteListaAdaptador.MyViewHolder> {

    private Context context;
    private List<Deporte> listaDeportes = new ArrayList<>();

    public DeporteListaAdaptador(Context p_context, List<Deporte> p_listaDeportes){
        this.context = p_context;
        this.listaDeportes = p_listaDeportes;
    }

    @NonNull
    @Override
    public DeporteListaAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.deporte_fila,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeporteListaAdaptador.MyViewHolder holder, int position) {
        holder._deporteFilaNombre.setText(listaDeportes.get(position).getNombre()+"");
        holder._deporteFilaDescripcion.setText(listaDeportes.get(position).getDescripcion()+"");
        holder._deporteFilaIrACentros.setOnClickListener(view -> {
            AlertDialog.Builder ventana = new AlertDialog.Builder(context);
            ventana.setTitle("Confirmar");
            ventana.setMessage("Desea ir a las Sedes del deporte " + listaDeportes.get(position).getNombre()+"?");
            ventana.setNegativeButton("No",null);
            ventana.setPositiveButton("Si",(dialogInterface, i) -> {
                Intent intent = new Intent(context, SedeListarActivity.class);
                context.startActivity(intent);
            });
            ventana.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return listaDeportes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView _deporteFilaNombre, _deporteFilaDescripcion;
        ImageButton _deporteFilaIrACentros;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _deporteFilaNombre = itemView.findViewById(R.id.DeporteFilaNombre);
            _deporteFilaDescripcion = itemView.findViewById(R.id.DeporteFilaDescripcion);
            _deporteFilaIrACentros = itemView.findViewById(R.id.DeporteFilaIrACentros);
        }
    }

}
