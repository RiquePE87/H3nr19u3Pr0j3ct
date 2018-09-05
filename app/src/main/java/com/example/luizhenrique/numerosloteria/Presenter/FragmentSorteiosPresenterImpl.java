package com.example.luizhenrique.numerosloteria.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.luizhenrique.numerosloteria.Adapter.ResultadosAdapter;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class FragmentSorteiosPresenterImpl implements FragmentSorteiosPresenter {

    Context ctx;
    public List<Resultado> resultados;
    public ResultadosAdapter adapter;
    public NetworkInfo info;
    public ConnectivityManager cm;
    SharedPreferences sharedPreferences;

    public FragmentSorteiosPresenterImpl(Context ctx){

        this.ctx = ctx;
    }

     public List<Resultado> carregarResultados(){

         sharedPreferences = ctx.getSharedPreferences("app",MODE_PRIVATE);
         Set<String> jogos = sharedPreferences.getStringSet("jogos",null);

         List<String> j = new ArrayList<>(jogos);

         Collections.sort(j);

        List<Resultado> resultadoList = new ArrayList<Resultado>();

        if (verificarConexao() == true){
            try{

                for (String s: j){
                    resultadoList.add(new ResultadoTask().execute(s.toLowerCase()).get());
                }

            }catch (Exception ex){

                ex.printStackTrace();
            }

        }else{

            try {

                for (String s: j){
                    resultadoList.add(new ResultadoService().carregarResultadoOffline(s.toLowerCase()));
                }

            } catch (Exception e) {
                e.printStackTrace();
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
