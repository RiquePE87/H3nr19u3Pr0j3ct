package com.example.luizhenrique.numerosloteria.Presenter;

import android.content.Context;

import com.example.luizhenrique.numerosloteria.Adapter.JogosAdapter;
import com.example.luizhenrique.numerosloteria.Services.RealmServices;

public class FragmentConsultaJogosPresenterImpl implements FragmentConsultaJogosPresenter {

    public JogosAdapter adapter;
    Context context;
    RealmServices realmServices;

    public FragmentConsultaJogosPresenterImpl(Context ctx){

        this.context = ctx;
        realmServices = new RealmServices(context);
    }

    @Override
    public JogosAdapter setJogosAdapter() {


        adapter = new JogosAdapter(context,realmServices.carregarJogos());

        return adapter;
    }
}
