package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.Resultado;

import java.util.ArrayList;

public interface DetalhesJogoPresenter {

    ArrayList<Integer> verificarNumerosAcertos(Resultado resultado, Jogo jogo);
    float verificarPremiacao(Resultado resultado, Jogo jogo);
    float verificarPremiacaoTime(Resultado resultado, Jogo jogo);
    void getResultadoAnterior(String tipoJogo, int sorteio);
}
