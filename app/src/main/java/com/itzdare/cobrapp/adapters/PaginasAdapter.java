package com.itzdare.cobrapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.itzdare.cobrapp.fragments.AgregarFragment;
import com.itzdare.cobrapp.fragments.CalculadoraFragment;
import com.itzdare.cobrapp.fragments.MostrarFragment;

public class PaginasAdapter extends FragmentStateAdapter {



    public PaginasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new CalculadoraFragment();
            case 1 :
                return new AgregarFragment();
            case 2 :
                return new MostrarFragment();
            
            default:
                return new CalculadoraFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }




}
