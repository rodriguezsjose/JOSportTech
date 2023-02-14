package com.grupouno.josporttech;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProv extends RecyclerView.Adapter<AdaptadorProv.MyViewHolder> {
    private Context c;

    private List<Proveedor> listprov =  new ArrayList<>();
    public AdaptadorProv(Context c, List<Proveedor> listprov){
        this.c = c;
        this.listprov = listprov;
    }

    


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(c);
        View vi = inf.inflate(R.layout.fila_prov, parent, false);
        return new MyViewHolder(vi);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filaNombre.setText(listprov.get(position).getId()+"");
        holder.filaDireccion.setText(listprov.get(position).getNom()+"");
        holder.filaHorario.setText(listprov.get(position).getDir()+"");
       // holder.btnVer.setOnClickListener(v -> {
      //     Intent intent = new Intent(c, ActivityDetalleReclamo.class);
      //    c.startActivity(intent);
      //  });



      //  holder.btnVer.setOnClickListener(v -> {
           // Intent intent = new Intent(c, ActivityDetalleProveedor.class);
           // intent.putExtra("p_id", listprov.get(position).getId()+"");
           // intent.putExtra("p_nom", listprov.get(position).getNom()+"");
            //intent.putExtra("p_dir", listprov.get(position).getDir()+"");
            //intent.putExtra("p_hor", listprov.get(position).getHor()+"");
            //intent.putExtra("p_prov", listprov.get(position).getProv()+"");
            //intent.putExtra("p_dist", listprov.get(position).getDist()+"");
            //intent.putExtra("p_ref",listprov.get(position).getRef()+"");
            //intent.putExtra("p_dep", listprov.get(position).getDep()+"");
            //intent.putExtra("p_serv", listprov.get(position).getServ()+"");
            //intent.putExtra("p_gal", listprov.get(position).getGal()+"");
           // c.startActivity(intent);


       // });

    }

    @Override
    public int getItemCount() {
        return listprov.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView filaNombre, filaDireccion, filaHorario;
        ImageButton btnVer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaDireccion = itemView.findViewById(R.id.filaAnio);
            filaHorario = itemView.findViewById(R.id.filaTitulo);
            btnVer = itemView.findViewById(R.id.btnRegRec);

        }
    }
}
