package com.grupouno.josporttech.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.grupouno.josporttech.ActivityDetalleReclamo;
import com.grupouno.josporttech.R;
import com.grupouno.josporttech.entidad.Reclamo;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorRec extends RecyclerView.Adapter<AdaptadorRec.MyViewHolder> {
    private Context c;

    private List<Reclamo> listreclamo = new ArrayList<>();
    public AdaptadorRec(Context c, List<Reclamo> listreclamo){
        this.c = c;
        this.listreclamo    = listreclamo;
    }
    


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(c);
        View vi = inf.inflate(R.layout.fila_rec, parent, false);
        return new MyViewHolder(vi);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filaPe.setText(listreclamo.get(position).getPeticion()+"");
        holder.filaRes.setText(listreclamo.get(position).getCodReserva()+"");
        holder.filaFec.setText(listreclamo.get(position).getFecha()+"");
        holder.btnVer.setOnClickListener(v -> {
          Intent intent = new Intent(c, ActivityDetalleReclamo.class);
          intent.putExtra("p_id", listreclamo.get(position).getId()+"");
          intent.putExtra("p_pet", listreclamo.get(position).getPeticion()+"");
          intent.putExtra("p_res", listreclamo.get(position).getCodReserva()+"");
          intent.putExtra("p_fec", listreclamo.get(position).getFecha()+"");
          intent.putExtra("p_ced", listreclamo.get(position).getCentroD()+"");
          intent.putExtra("p_med", listreclamo.get(position).getMedioContacto()+"");
          intent.putExtra("p_con", listreclamo.get(position).getContacto()+"");
          intent.putExtra("p_cor", listreclamo.get(position).getCorreo()+"");
          intent.putExtra("p_mot", listreclamo.get(position).getMotivo()+"");
          intent.putExtra("p_des", listreclamo.get(position).getDescMotivo()+"");
          intent.putExtra("p_sus", listreclamo.get(position).getSustento()+"");


            c.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return listreclamo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView filaPe, filaRes, filaFec;
        ImageButton btnVer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaPe = itemView.findViewById(R.id.filaPet);
            filaRes = itemView.findViewById(R.id.filaCodRes);
            filaFec = itemView.findViewById(R.id.filafecha);
            btnVer = itemView.findViewById(R.id.btn_verRec);
        }
    }
}
