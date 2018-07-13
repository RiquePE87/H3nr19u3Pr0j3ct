package com.example.luizhenrique.numerosloteria.Services;

import android.os.AsyncTask;

import com.example.luizhenrique.numerosloteria.Model.Sorteio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SorteioTask {

    public Sorteio sorteio_l;

    public Sorteio carregarResultado(String tipoJogo, String num_sorteio){

        LoteriaService loteriaService = RetrofitConfig.getRetrofitInstance().create(LoteriaService.class);

        Call<Sorteio> call = loteriaService.buscarLoteria1(tipoJogo, num_sorteio);

        String calli = call.request().url().toString();

        call.enqueue(new Callback<Sorteio>() {
            @Override
            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {


                sorteio_l = response.body();
            }

            @Override
            public void onFailure(Call<Sorteio> call, Throwable t) {

                String erro = t.getMessage();
            }
        });

        return sorteio_l;
    }

    public Sorteio carregarResultado(String tipoJogo){


            LoteriaService loteriaService = RetrofitConfig.getRetrofitInstance().create(LoteriaService.class);

            Call<Sorteio> call = loteriaService.buscarLoteria2(tipoJogo);

            String calli = call.request().url().toString();

            call.enqueue(new Callback<Sorteio>() {
                @Override
                public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {

                    sorteio_l = response.body();
                }

                @Override
                public void onFailure(Call<Sorteio> call, Throwable t) {
                    String erro = t.getMessage();
                }
            });

        return sorteio_l;
    }
}
