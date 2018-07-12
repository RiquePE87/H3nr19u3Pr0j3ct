package com.example.luizhenrique.numerosloteria.Services;

import android.os.AsyncTask;

import com.example.luizhenrique.numerosloteria.Model.Sorteio;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SorteioTask extends AsyncTask<String,Void,Sorteio> {

    Sorteio sorteio_l;

    public Sorteio carregarResultado(String tipoJogo, String num_sorteio){

        Call<Sorteio> call = new RetrofitConfig().getLoteriaService().buscarLoteria(tipoJogo,num_sorteio);

        call.enqueue(new Callback<Sorteio>() {
            @Override
            public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {

                sorteio_l = response.body();
            }

            @Override
            public void onFailure(Call<Sorteio> call, Throwable t) {

            }
        });

        return sorteio_l;
    }

    public Sorteio carregarResultado(String tipoJogo){

        try {
            Call<Sorteio> call = new RetrofitConfig().getLoteriaService().buscarLoteria(tipoJogo);

            call.enqueue(new Callback<Sorteio>() {
                @Override
                public void onResponse(Call<Sorteio> call, Response<Sorteio> response) {

                    sorteio_l = response.body();

                }

                @Override
                public void onFailure(Call<Sorteio> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sorteio_l;
    }
    @Override
    protected Sorteio doInBackground(String... strings) {

        try {
            if (strings.length == 2) {
                return carregarResultado(strings[0], strings[1]);
            } else {
                return carregarResultado(strings[0]);
            }
        }catch (Exception ex){

        }

        return null;
    }
}
