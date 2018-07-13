package com.example.luizhenrique.numerosloteria.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.luizhenrique.numerosloteria.Adapter.ResultadosAdapter;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Model.Sorteio;
import com.example.luizhenrique.numerosloteria.Retrofit.CtrlResultado;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;
import com.example.luizhenrique.numerosloteria.Services.SorteioTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragmentSorteiosPresenterImpl implements FragmentSorteiosPresenter {

    Context ctx;
    public List<Resultado> resultados;
    public ResultadosAdapter adapter;
    public NetworkInfo info;
    public ConnectivityManager cm;
    Sorteio sorteio;

    public FragmentSorteiosPresenterImpl(Context ctx){

        this.ctx = ctx;


    }

     public List<Resultado> carregarResultados(){


        List<Resultado> resultadoList = new ArrayList<Resultado>();

        if (verificarConexao() == true){
            try{

                //  resultadoList.add(new ResultadoDuplaTask().execute("dupla-sena").get());
                resultadoList.add(new ResultadoTask().execute("mega-sena").get());
                resultadoList.add(new ResultadoTask().execute("lotomania").get());
                resultadoList.add(new ResultadoTask().execute("lotofacil").get());
                resultadoList.add(new ResultadoTask().execute("quina").get());
                resultadoList.add(new ResultadoTask().execute("timemania").get());

            }catch (Exception ex){

            }
        }

         return resultadoList;
    }

    @Override
    public ResultadosAdapter setSorteioAdapter() {

        resultados = carregarResultados();

        adapter = new ResultadosAdapter(ctx,resultados);

        return adapter;
    }

    @Override
    public boolean verificarConexao() {

        cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        info = cm.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }


}
