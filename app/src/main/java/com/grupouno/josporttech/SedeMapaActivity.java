package com.grupouno.josporttech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;

public class SedeMapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    String _nombre="UPC - Monterrico";
    double _latitud=-12.103917;
    double _longitud=-76.963669;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sede_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.SedeMapa);
        mapFragment.getMapAsync(this);
        if(getIntent().hasExtra("p_nombre")){
            _nombre =  getIntent().getStringExtra("p_nombre");
            _latitud = Double.parseDouble(getIntent().getStringExtra("p_latitud"));
            _longitud = Double.parseDouble(getIntent().getStringExtra("p_longitud"));
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googlemap){
        mMap = googlemap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //
        LatLng ubicacion = new LatLng(_latitud, _longitud);
        mMap.addMarker(new MarkerOptions().position(ubicacion).title(_nombre));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,16));
    }

}