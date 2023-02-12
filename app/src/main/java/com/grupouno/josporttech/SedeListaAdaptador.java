package com.grupouno.josporttech;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupouno.josporttech.entidad.Sede;

import java.util.ArrayList;
import java.util.List;

public class SedeListaAdaptador extends RecyclerView.Adapter<SedeListaAdaptador.MyViewHolder> {

    private Context context;
    private List<Sede> listaSedes = new ArrayList<>();

    public SedeListaAdaptador(Context p_context, List<Sede> p_listaSedes){
        this.context = p_context;
        this.listaSedes = p_listaSedes;
    }

    @NonNull
    @Override
    public SedeListaAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sede_fila,parent,false);
        return new SedeListaAdaptador.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SedeListaAdaptador.MyViewHolder holder, int position) {
        holder._sedeFilaNombre.setText(listaSedes.get(position).getNombre()+"");
        holder._sedeFilaDescripcion.setText(listaSedes.get(position).getDescripcion()+"");
        holder._sedeFilaLatitud.setText(listaSedes.get(position).getLatitud()+"");
        holder._sedeFilaLongitud.setText(listaSedes.get(position).getLongitud()+"");
        holder._sedeFilaTelefono.setText(listaSedes.get(position).getTelefono()+"");
        holder._deporteFilaIrAlMapa.setOnClickListener(view -> {
            AlertDialog.Builder ventana = new AlertDialog.Builder(context);
            ventana.setTitle("Confirmar");
            ventana.setMessage("Desea ir a la Sede " + listaSedes.get(position).getNombre()+"?");
            ventana.setNegativeButton("No",null);
            ventana.setPositiveButton("Si",(dialogInterface, i) -> {
                Intent intent = new Intent(context, SedeMapaActivity.class);
                intent.putExtra("p_nombre",listaSedes.get(position).getNombre()+"");
                intent.putExtra("p_latitud",listaSedes.get(position).getLatitud()+"");
                intent.putExtra("p_longitud",listaSedes.get(position).getLongitud()+"");
                context.startActivity(intent);
            });
            ventana.create().show();
        });
        holder._deporteFilaLlamar.setOnClickListener(view -> {
            AlertDialog.Builder ventana = new AlertDialog.Builder(context);
            ventana.setTitle("Confirmar");
            ventana.setMessage("Desea llamar a la Sede " + listaSedes.get(position).getNombre()+"?");
            ventana.setNegativeButton("No",null);
            ventana.setPositiveButton("Si",(dialogInterface, i) -> {
                Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+listaSedes.get(position).getTelefono()));
                context.startActivity(iCall);
            });
            ventana.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return listaSedes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView _sedeFilaNombre, _sedeFilaDescripcion, _sedeFilaLatitud, _sedeFilaLongitud, _sedeFilaTelefono;
        ImageButton _deporteFilaIrAlMapa, _deporteFilaLlamar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _sedeFilaNombre = itemView.findViewById(R.id.SedeFilaNombre);
            _sedeFilaDescripcion = itemView.findViewById(R.id.SedeFilaDescripcion);
            _sedeFilaLatitud = itemView.findViewById(R.id.SedeFilaLatitud);
            _sedeFilaLongitud = itemView.findViewById(R.id.SedeFilaLongitud);
            _sedeFilaTelefono = itemView.findViewById(R.id.SedeFilaTelefono);
            _deporteFilaIrAlMapa = itemView.findViewById(R.id.SedeFilaIrAlMapa);
            _deporteFilaLlamar = itemView.findViewById(R.id.SedeFilaLlamar);
        }
    }



}

