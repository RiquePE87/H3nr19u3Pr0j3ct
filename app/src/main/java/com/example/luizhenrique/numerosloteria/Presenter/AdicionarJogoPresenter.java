package com.example.luizhenrique.numerosloteria.Presenter;

public interface AdicionarJogoPresenter {

    int jogarProximoConcurso(String tipoJogo);

    int[] numerosMaisSorteados(String tipoJogo, int numeroDezenas, int minAposta);

    boolean validarJogo(String numeros, String sorteio);


}
