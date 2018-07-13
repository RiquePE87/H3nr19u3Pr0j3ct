package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Services.LoteriaService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://lotodicas.com.br/api/";

    RetrofitConfig(){

    }

    public static Retrofit getRetrofitInstance(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
