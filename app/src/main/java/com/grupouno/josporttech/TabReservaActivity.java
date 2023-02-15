package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.grupouno.josporttech.adaptador.ReservaViewAdapter;
import com.grupouno.josporttech.fragments.ReservasActivasFragment;

import java.util.ArrayList;

public class TabReservaActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ReservaViewAdapter reservaViewAdapter;
    FloatingActionButton btnMicrofono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_reserva);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        reservaViewAdapter = new ReservaViewAdapter(this);
        viewPager2.setAdapter(reservaViewAdapter);

        btnMicrofono = findViewById(R.id.btnMicrofono);
        btnMicrofono.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"es-MX");
            startActivityForResult(intent,200);
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK){
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String voice = arrayList.get(0);
            //Toast.makeText(this, voice, Toast.LENGTH_SHORT).show();
            Bundle args =new Bundle();
            args.putString("param1",voice);
            try{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frgReservaActiva, ReservasActivasFragment.class,args)
                        .setReorderingAllowed(true)
                        .commit();
            }catch (Exception e)
            {
                Log.d("==>",e.getMessage());
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }


    }
}