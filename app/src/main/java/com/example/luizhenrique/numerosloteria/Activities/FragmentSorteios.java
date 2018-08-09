package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Presenter.FragmentSorteiosPresenter;
import com.example.luizhenrique.numerosloteria.Presenter.FragmentSorteiosPresenterImpl;
import com.example.luizhenrique.numerosloteria.R;

public class FragmentSorteios extends ListFragment  {

    public Resultado res;
    public ListView listView;
    public View view;
    public NetworkInfo info;
    public ProgressBar progressBar;
    public FragmentSorteiosPresenter fragmentSorteiosPresenter;

    public FragmentSorteios() {

    }

    public static FragmentSorteios newInstance() {

         FragmentSorteios fragment;
         fragment = new FragmentSorteios();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        fragmentSorteiosPresenter = new FragmentSorteiosPresenterImpl(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        view = getView();
        listView = getListView();

        setListAdapter(fragmentSorteiosPresenter.setSorteioAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Resultado res = (Resultado) listView.getItemAtPosition(position);

                Intent it = new Intent(getContext(),DetalhesSorteio.class);
//                it.putExtra("tipoJogo",res.getTipo());
//                it.putExtra("ultimoSorteio", res.getNumero());
                it.putExtra("flagConsulta",false);
                it.putExtra("resultado",res);
                startActivity(it);
            }
        });
    }
            public void onRefresh() {

                setListAdapter(fragmentSorteiosPresenter.setSorteioAdapter());

            }

    public void exibirProgress(boolean exibir) {
        progressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_refresh2:
                onRefresh();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
