package com.grupouno.josporttech.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupouno.josporttech.R;
import com.grupouno.josporttech.adaptador.AdaptadorListarReserva;
import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.modelo.DAOReserva;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReservasAnuladasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservasAnuladasFragment extends Fragment {

    private Context globalContext = null;

    FloatingActionButton btnNuevo;
    RecyclerView rvReserva;

    DAOReserva daoReserva;
    List<Reserva> listaReservas = new ArrayList<>();
    AdaptadorListarReserva adaptador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReservasAnuladasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservasAnuladasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservasAnuladasFragment newInstance(String param1, String param2) {
        ReservasAnuladasFragment fragment = new ReservasAnuladasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        globalContext = this.getContext();
        daoReserva = new DAOReserva(globalContext);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservas_activas, container, false);

        return view;
    }
    private void mostrarReservas() {
        listaReservas = daoReserva.obtenerListaReserva("ANULADA");
        adaptador = new AdaptadorListarReserva(globalContext, listaReservas);
        rvReserva.setAdapter(adaptador);
        rvReserva.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void asignarReferencias(){
        btnNuevo = getView().findViewById(R.id.btnNuevo);
        btnNuevo.setVisibility(View.INVISIBLE);
        rvReserva = getView().findViewById(R.id.rvReserva);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asignarReferencias();
        daoReserva.abrirBD();
        mostrarReservas();
    }
}