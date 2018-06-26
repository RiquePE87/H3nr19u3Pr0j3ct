package com.example.luizhenrique.numerosloteria.Retrofit;

import com.example.luizhenrique.numerosloteria.Model.Resultado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CtrlResultado {

    Resultado resultado;

    public Resultado carregarResultado(String tipoJogo, String sorteio){

        Call<Resultado> call = new RetrofitConfig().getLoteriaService().buscarLoteria(tipoJogo,sorteio);

        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                resultado = response.body();

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

        return resultado;
    }

    public Resultado carregarResultado(String tipoJogo){


        Call<Resultado> call = new RetrofitConfig().getLoteriaService().buscarLoteria(tipoJogo);

        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                resultado = response.body();

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

        return resultado;
    }

}

