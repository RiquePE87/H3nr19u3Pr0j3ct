package com.example.luizhenrique.numerosloteria.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luizhenrique.numerosloteria.Activities.FragmentConsultaJogos;
import com.example.luizhenrique.numerosloteria.Activities.FragmentSorteios;

/**
 * Created by luizhenrique on 09/08/17.
 */

public class AbasPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_TABS = 2;

    public AbasPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return FragmentSorteios.newInstance();
            case 1:
                return FragmentConsultaJogos.newInstance();
            default:
                return FragmentConsultaJogos.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return "Sorteios";
        }

        if (position == 1){

            return "Minhas Apostas";
        }

        return "Sorteios";
    }
}

