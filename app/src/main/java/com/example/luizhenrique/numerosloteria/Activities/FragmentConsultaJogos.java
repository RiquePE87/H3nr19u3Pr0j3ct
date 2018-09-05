package com.example.luizhenrique.numerosloteria.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Presenter.FragmentConsultaJogosPresenter;
import com.example.luizhenrique.numerosloteria.Presenter.FragmentConsultaJogosPresenterImpl;
import com.example.luizhenrique.numerosloteria.Services.RealmServices;


public class FragmentConsultaJogos extends ListFragment {

    View layout;
    ListView listView;
    FragmentConsultaJogosPresenter fragmentConsultaJogosPresenter;

    public FragmentConsultaJogos() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout = getView();
        listView = getListView();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Context context = getContext();
                final Jogo jogo = (Jogo) listView.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmar")
                        .setMessage("Tem certeza que deseja excluir esta aposta?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new RealmServices(getContext()).ExcluirJogo(jogo);
                                Toast.makeText(context,"Aposta excluída com sucesso!",Toast.LENGTH_SHORT).show();
                                setListAdapter(fragmentConsultaJogosPresenter.setJogosAdapter());
                            }
                        })
                        .setNegativeButton("Cancelar",null)
                        .create()
                        .show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Activity activity = getActivity();

                Jogo jogo = (Jogo)listView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(),DetalhesJogo.class);
                intent.putExtra("id",jogo.id);
                startActivity(intent);
            }
        });

        setListAdapter(fragmentConsultaJogosPresenter.setJogosAdapter());
    }

    public static FragmentConsultaJogos newInstance() {
        FragmentConsultaJogos fragment = new FragmentConsultaJogos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentConsultaJogosPresenter = new FragmentConsultaJogosPresenterImpl(getContext());
        if (getArguments() != null) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setListAdapter(fragmentConsultaJogosPresenter.setJogosAdapter());
    }
}
