package com.grupouno.josporttech.adaptador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.grupouno.josporttech.fragments.ReservasActivasFragment;
import com.grupouno.josporttech.fragments.ReservasAnuladasFragment;
import com.grupouno.josporttech.fragments.ReservasCerradasFragment;
import com.grupouno.josporttech.fragments.ReservasVencidasFragment;

public class ReservaViewAdapter extends FragmentStateAdapter {
    public ReservaViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ReservasActivasFragment();
            case 1:
                return new ReservasCerradasFragment();
            case 2:
                return new ReservasVencidasFragment();
            case 3:
                return new ReservasAnuladasFragment();
            default:
                return new ReservasActivasFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
