package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.JogoManager;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;

import java.util.ArrayList;

public class DetalhesJogoPresenterImpl implements DetalhesJogoPresenter {

    JogoManager  jogoManager;



    public DetalhesJogoPresenterImpl(){

        jogoManager = new JogoManager();
    }

// Verifica quais numeros jogados foram acertados

    public ArrayList<Integer> verificarNumerosAcertos(Resultado resultado, Jogo jogo){

        ArrayList<Integer> numerosAcertos = new ArrayList<>();

        int[] numerosInt = GeradorDeNumeros.ParseToInt(jogo);

        if (resultado == null){
            return numerosAcertos;
        }else{

            int[] numerosSorteados = resultado.getSorteio();

            for (int i = 0; i < numerosInt.length; i++)
            {
                for (int j = 0; j < numerosSorteados.length; j++)
                {
                    if (numerosInt[i] == numerosSorteados[j])
                    {
                        numerosAcertos.add(numerosInt[i]);
                    }
                }
            }
            return numerosAcertos;
        }
    }

    // verifica quanto a apsota rendeu em reais

    public float verificarPremiacao(Resultado res, Jogo jogo){

        float valorPremio = 0;
        int[] premiacaoes;
        int count = 0;

        int acertos = verificarNumerosAcertos(res,jogo).size();

        premiacaoes = jogoManager.getAcertos(jogo.tipoJogo.toLowerCase());

        //count = premiacaoes.length;

        for (int num: premiacaoes){

            if (num == acertos){

                valorPremio = res.getRateio()[count];

            }else{
                count++;
            }
        }

        return valorPremio;

        }

    @Override
    public float verificarPremiacaoTime(Resultado resultado, Jogo jogo) {

        float premioTimeCoracao = 0;

        if (resultado.getTime().equals(jogo.timeDoCoracao)){

            premioTimeCoracao = resultado.getRateio()[5];

        }

        return premioTimeCoracao;
    }
}

