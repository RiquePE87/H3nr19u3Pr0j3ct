package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ResultadoService {


    public static Resultado carregarResultado(String tipo, String sorteio) {

        Resultado res = new Resultado();
        String url = "https://www.lotodicas.com.br/api/" + tipo.toLowerCase() + "/" + sorteio + "";

        try {
            Reader reader = new InputStreamReader((new URL(url).openStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Resultado.class);
            res.setTipo(tipo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Resultado carregarResultado(String tipo) {

        Resultado res = new Resultado();
        String url = "https://www.lotodicas.com.br/api/" + tipo.toLowerCase();

        try {
            Reader reader = new InputStreamReader((new URL(url).openStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Resultado.class);
            res.setTipo(tipo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    public static int[] verificarMaisSorteados(String sorteio, String tipo, int range, int dezenas, int quantidade) {

        ArrayList<Object> sorteios = new ArrayList<Object>() {
        };
        Map<Integer, Integer> numeros = new HashMap<Integer, Integer>();
        int[] n;

        if (tipo.equals("Dupla-Sena")){
            n = new int[range * 20];
        }else {
            n = new int[range * 10];
        }

        int count = 0;
        int[] maisSorteados;


        int numSorteio = Integer.parseInt(sorteio);

        for (int i = 0; i < 10; i++) {

            Resultado res = null;

            try {
                res = new ResultadoTask().execute(tipo, String.valueOf(numSorteio)).get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            sorteios.add(res.getSorteio());
            numSorteio--;
        }

        if (tipo.equals("Dupla-Sena")){

            ArrayList<ArrayList<Integer>> nums;

            for (Object num : sorteios){

                nums = (ArrayList<ArrayList<Integer>>) num;

                for (int j = 0; j < nums.size();j++){

                    for (int i=0;i < range;i++){

                        n[count] = nums.get(j).get(i);
                        count++;
                    }
                }

                nums = null;
            }

            Arrays.sort(n);

            maisSorteados = GeradorDeNumeros.gerarMaisSorteadas(n, dezenas, quantidade);

            Arrays.sort(maisSorteados);

        }else{
            for (Object num : sorteios) {

                ArrayList<Integer> nums = (ArrayList<Integer>) num;

                for (int i = 0; i < nums.size(); i++) {
                    n[count] = nums.get(i);
                    count++;
                }
            }

            Arrays.sort(n);

            maisSorteados = GeradorDeNumeros.gerarMaisSorteadas(n, dezenas, quantidade);

            Arrays.sort(maisSorteados);
        }



        return maisSorteados;
    }

    public static String formatarData(String d) {

        String dataFormatada = d.substring(8, 10) + "/" + d.substring(5, 7) + "/" + d.substring(0, 4);

        return dataFormatada;
    }
}

