package com.example.luizhenrique.numerosloteria.Presenter;

import android.content.Context;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.Resultado;

import java.util.ArrayList;

public interface DetalhesJogoPresenter {

    ArrayList<Integer> verificarNumerosAcertos(Resultado resultado, Jogo jogo);
    Object verificarPremiacao(Resultado resultado, Jogo jogo);
    Object verificarPremiacaoTime(Resultado resultado, Jogo jogo);
    Object verificarPremiacaoMes(Resultado resultado, Jogo jogo);
    ArrayList<Float> verificarPremiacaoDuplaSena(Resultado res, Jogo jogo);
    ArrayList<ArrayList<Integer>> verificarNumerosAcertosDuplaSena(Resultado resultado,Jogo jogo);
    void getResultadoAnterior(String tipoJogo, int sorteio);
    boolean verificarConexao();
    Jogo carregarRealmJogo(int id, Context context);
    Resultado carregarResultadoOff(String filename);
}
