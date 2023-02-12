package com.grupouno.josporttech.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.grupouno.josporttech.R;
import com.grupouno.josporttech.ReservaActivity;
import com.grupouno.josporttech.entidad.Reserva;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorListarReserva extends RecyclerView.Adapter<AdaptadorListarReserva.MyViewHolder> {

    private Context context;
    private List<Reserva> listaReserva = new ArrayList<>();

    public AdaptadorListarReserva(Context context, List<Reserva> listaReserva){
        this.context = context;
        this.listaReserva = listaReserva;
    }

    @NonNull
    @Override
    public AdaptadorListarReserva.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_listar_reserva,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListarReserva.MyViewHolder holder, int position) {
        holder.filaCentro.setText(listaReserva.get(position).getDescCentro());
        holder.filaDeporte.setText(listaReserva.get(position).getDescDeporte());
        holder.filaFecha.setText(listaReserva.get(position).getFecha());
        holder.filaHora.setText(listaReserva.get(position).getHora());
        holder.btnEditar.setVisibility(View.INVISIBLE);
        holder.btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReservaActivity.class);
            intent.putExtra("p_id", listaReserva.get(position).getId()+"");
            intent.putExtra("p_idCentro", listaReserva.get(position).getIdCentro()+"");
            intent.putExtra("p_idSede", listaReserva.get(position).getIdSede()+"");
            intent.putExtra("p_idDeporte", listaReserva.get(position).getIdDeporte()+"");
            intent.putExtra("p_fecha", listaReserva.get(position).getFecha()+"");
            intent.putExtra("p_hora", listaReserva.get(position).getHora()+"");
            context.startActivity(intent);
        });
        holder.cvReserva.setOnClickListener(view -> {
            //Toast.makeText(view.getContext(), "Prueba", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ReservaActivity.class);
            intent.putExtra("p_id", listaReserva.get(position).getId()+"");
            intent.putExtra("p_idCentro", listaReserva.get(position).getIdCentro()+"");
            intent.putExtra("p_descCentro", listaReserva.get(position).getDescCentro()+"");
            intent.putExtra("p_idSede", listaReserva.get(position).getIdSede()+"");
            intent.putExtra("p_descSede", listaReserva.get(position).getDescSede()+"");
            intent.putExtra("p_idDeporte", listaReserva.get(position).getIdDeporte()+"");
            intent.putExtra("p_descDeporte", listaReserva.get(position).getDescDeporte()+"");
            intent.putExtra("p_fecha", listaReserva.get(position).getFecha()+"");
            intent.putExtra("p_hora", listaReserva.get(position).getHora()+"");
            intent.putExtra("p_estado", listaReserva.get(position).getEstado()+"");
            intent.putExtra("p_idMotivoAnulacion", listaReserva.get(position).getIdMotivoAnulacion()+"");
            context.startActivity(intent);
        });
        int flagPagado = listaReserva.get(position).getFlagPagado();
        holder.imgReserva.setImageResource(R.drawable.ic_calendar_sin_pagar);
        if ( flagPagado == 1 ){
            holder.imgReserva.setImageResource(R.drawable.ic_calendar_pagado);
        }
    }

    @Override
    public int getItemCount() {
        return listaReserva.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView filaCentro, filaDeporte, filaFecha, filaHora;
        ImageButton btnEditar;
        CardView cvReserva;
        ImageView imgReserva;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            filaCentro = itemView.findViewById(R.id.filaCentro);
            filaDeporte = itemView.findViewById(R.id.filaDeporte);
            filaFecha = itemView.findViewById(R.id.filaFecha);
            filaHora = itemView.findViewById(R.id.filaHora);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            cvReserva = itemView.findViewById(R.id.cvReserva);
            imgReserva = itemView.findViewById(R.id.imgReserva);
            //cvReserva.

        }
    }
}
