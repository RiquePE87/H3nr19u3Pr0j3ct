package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Model.Sorteio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoteriaService {

    @GET("{tipojogo}/{concurso}")
    Call<Sorteio> buscarLoteria1(@Path("tipojogo")String tipoJogo, @Path("concurso") String concurso);

    @GET("{tipojogo}")
    Call<Sorteio> buscarLoteria2(@Path("tipojogo")String tipoJogo);

}
