package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Model.Sorteio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoteriaService {

    @GET("{tipojogo}/{concurso}")
    Call<Sorteio> buscarLoteria(@Path("tipojogo")String tipoJogo, @Path("concurso") String concurso);

    @GET("{tipojogo}/{concurso}")
    Call<Sorteio> buscarLoteria(@Path("tipojogo")String tipoJogo);

}
