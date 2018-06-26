package com.example.luizhenrique.numerosloteria.Presenter;

import android.text.TextUtils;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;
import com.example.luizhenrique.numerosloteria.View.AdicionarJogoView;

import java.util.concurrent.ExecutionException;

public class AdicionarJogoPresenterImpl implements AdicionarJogoPresenter {

    private AdicionarJogoView adicionarJogoView;

    public AdicionarJogoPresenterImpl(AdicionarJogoView adicionarJogoView) {

        this.adicionarJogoView = adicionarJogoView;
    }

    @Override
    public boolean validarJogo(String numeros, String sorteio) {

        if (TextUtils.isEmpty(numeros)){
            adicionarJogoView.setNumerosError();

            return false;
        }

        if (TextUtils.isEmpty(sorteio)){
            adicionarJogoView.setSorteioError();

            return false;
        }

        return true;
    }

    @Override
    public int jogarProximoConcurso(String tipoJogo) {

        Resultado res;
        int proximoConcurso = 0;

        try {
            res = new ResultadoTask().execute(tipoJogo.toLowerCase()).get();
            proximoConcurso = res.getNumero() + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return proximoConcurso;
    }

    @Override
    public int[] numerosMaisSorteados(String tipoJogo, int numeroDezenas, int minAposta) {

        Resultado resultadoAtual;

        int[] intNumerosMaisSorteados = null;

            try {

                resultadoAtual = new ResultadoTask().execute(tipoJogo).get();
                intNumerosMaisSorteados = ResultadoService.verificarMaisSorteados(String.valueOf(resultadoAtual.getNumero()), tipoJogo, resultadoAtual.getSorteio().length, numeroDezenas, minAposta);


        } catch (Exception ex) {

            adicionarJogoView.exibirToast(ex.getMessage());
        }
        adicionarJogoView.exibirPublicidade();
        return intNumerosMaisSorteados;


    }
}
