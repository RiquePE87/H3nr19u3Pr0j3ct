package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Services.LoteriaService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    RetrofitConfig(){

       retrofit = new Retrofit.Builder()
                .baseUrl("http://lotodicas.com.br/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public LoteriaService getLoteriaService(){

        return this.retrofit.create(LoteriaService.class);

    }
}
