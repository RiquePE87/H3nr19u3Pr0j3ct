package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Services.JogoManager;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;
import com.example.luizhenrique.numerosloteria.View.DetalhesJogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DetalhesJogoPresenterImpl implements DetalhesJogoPresenter {

    private JogoManager  jogoManager;
    private DetalhesJogoView detalhesJogoView;

    public DetalhesJogoPresenterImpl(DetalhesJogoView detalhesJogoView){

        jogoManager = new JogoManager();
        this.detalhesJogoView = detalhesJogoView;
    }

// Verifica quais numeros jogados foram acertados

    public ArrayList<Integer> verificarNumerosAcertos(Resultado resultado, Jogo jogo){

        ArrayList<Integer> numerosAcertos = new ArrayList<>();

        int[] numerosJogados = GeradorDeNumeros.ParseToInt(jogo);

        if (resultado == null){
            return numerosAcertos;
        }else{

            List<Object> numerosSorteados = resultado.getSorteio();

            for (int nums : numerosJogados) {
                for (Object numerosSorteado : numerosSorteados) {
                    if (numerosSorteado.equals(nums)) {
                        numerosAcertos.add(nums);
                    }
                }
            }
            return numerosAcertos;
        }
    }

    // verifica quanto a apsota rendeu em reais

    public Object verificarPremiacao(Resultado res, Jogo jogo){

        Object valorPremio = 0;
        int[] premiacaoes;
        int count = 0;

        int acertos = verificarNumerosAcertos(res,jogo).size();

        premiacaoes = jogoManager.getAcertos(jogo.tipoJogo.toLowerCase());

        //count = premiacaoes.length;

        for (int num: premiacaoes){

            if (num == acertos){

                valorPremio = res.getRateio().get(count);

            }else{
                count++;
            }
        }

        return valorPremio;

        }

    @Override
    public Object verificarPremiacaoTime(Resultado resultado, Jogo jogo) {

        Object premioTimeCoracao = 0;

        if (resultado.getTime().equals(jogo.timeDoCoracao)){

            premioTimeCoracao = resultado.getRateio().get(5);

        }

        return premioTimeCoracao;
    }

    @Override
    public void getResultadoAnterior(String tipoJogo, int sorteio) {

        String txt = "";

        try {
            Resultado resAnt = new ResultadoTask().execute(tipoJogo, String.valueOf(sorteio-1)).get();

            if (resAnt == null){

                txt = "Sorteio não ocorreu ainda!";
            }
            else {

                txt = "Sorteio ocorrerá dia "+ ResultadoService.formatarData(resAnt.getProximoData());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        detalhesJogoView.setTextView(txt);
    }
}

