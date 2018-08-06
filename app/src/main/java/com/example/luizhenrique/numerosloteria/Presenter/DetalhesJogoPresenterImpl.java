package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;
import com.example.luizhenrique.numerosloteria.Services.JogoManager;
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

        if (resultado.getNumero() == null){

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

    public ArrayList<ArrayList<Integer>> verificarNumerosAcertosDuplaSena(Resultado resultado,Jogo jogo) {

        ArrayList<ArrayList<Integer>> numerosAcertos = new ArrayList<>();
        numerosAcertos.add(new ArrayList<Integer>());
        numerosAcertos.add(new ArrayList<Integer>());

        int[] numerosJogados = GeradorDeNumeros.ParseToInt(jogo);

            List<Integer> numsJogo1 = (ArrayList<Integer>) resultado.getSorteio().get(0);
            List<Integer> numsJogo2 = (ArrayList<Integer>) resultado.getSorteio().get(1);


            for (int nums : numerosJogados) {
                for (Object numerosSorteado : numsJogo1) {
                    if (numerosSorteado.equals(nums)) {
                        numerosAcertos.get(0).add(nums);
                    }
                }
            }

            for (int nums : numerosJogados) {
                for (Object numerosSorteado : numsJogo2) {
                    if (numerosSorteado.equals(nums)) {
                        numerosAcertos.get(1).add(nums);
                    }
                }
            }

            return numerosAcertos;
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

        public ArrayList<Float> verificarPremiacaoDuplaSena(Resultado res, Jogo jogo){

            ArrayList<Float> valorPremio = new ArrayList<>();
            int[] premiacaoes;
            int count = 0;

            int acertos1 = verificarNumerosAcertosDuplaSena(res,jogo).get(0).size();
            int acertos2 = verificarNumerosAcertosDuplaSena(res,jogo).get(1).size();

            ArrayList<Float> rateioJogo1 =  (ArrayList<Float>) res.getRateio().get(0);
            ArrayList<Float> rateioJogo2 =  (ArrayList<Float>) res.getRateio().get(1);


            premiacaoes = jogoManager.getAcertos(jogo.tipoJogo.toLowerCase());

            for (int num: premiacaoes){

                if (num == acertos1){

                    valorPremio.add(rateioJogo1.get(count));

                }else{
                    count++;
                }
            }

            for (int num: premiacaoes){

                if (num == acertos2){

                    valorPremio.add(rateioJogo2.get(count));
                }else{
                    count++;
                }
            }

        return  valorPremio;
    }

    @Override
    public Object verificarPremiacaoTime(Resultado resultado, Jogo jogo) {

        Object premioTimeCoracao = 0;

        if (resultado.getTime().equals(jogo.timeDoCoracao)){

            premioTimeCoracao = resultado.getRateio().get(5);

        }

        return premioTimeCoracao;
    }

    public Object verificarPremiacaoMes(Resultado resultado, Jogo jogo){

        Object premiacaoMes = 0;

        if (resultado.getMes().equals(jogo.mesDeSorte)){
            premiacaoMes = resultado.getRateio().get(4);
        }

        return premiacaoMes;
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

