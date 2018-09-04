package com.example.luizhenrique.numerosloteria.Services;

import android.content.Context;

import com.example.luizhenrique.numerosloteria.BuildConfig;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
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

    public Context context;
    public static final String URL = "https://www.lotodicas.com.br/api/";
    public static final String PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/files/";

    public static String getFilename(String tipoJogo, String concurso){

        String filename;

        if (concurso == null){

            filename = PATH+tipoJogo+ ".json";
        }
        else {
            filename = PATH+tipoJogo +" "+concurso + ".json";
        }

        return filename;
    }


    public static Resultado carregarResultado(String tipo, String sorteio) {

        Resultado res = new Resultado();
        String url =  URL + tipo.toLowerCase() + "/" + sorteio;

        try {
            Reader reader = new InputStreamReader((new URL(url).openStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Resultado.class);
            res.setTipo(tipo);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Resultado carregarResultado(String tipo) {

        Resultado res = new Resultado();
        String url = URL + tipo.toLowerCase();

        try {
            Reader reader = new InputStreamReader((new URL(url).openStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.readValue(reader, Resultado.class);
            res.setTipo(tipo);
            salvarResultadosOffline(res,true);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    private static void salvarResultadosOffline(Resultado res, boolean isUltimoSorteio){

        ObjectMapper objectMapper = new ObjectMapper();

        String filename;

        if (isUltimoSorteio){

            filename = getFilename(res.getTipo(),null);

        }
        else {
            filename = getFilename(res.getTipo(),String.valueOf(res.getNumero()));
        }

        if (!new File(filename).exists()){
            try { objectMapper.writeValue(new File(filename),res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Resultado carregarResultadoOffline(String tipoJogo){

        ObjectMapper objectMapper = new ObjectMapper();
        Resultado resultado = new Resultado();
        String filename = PATH+tipoJogo+".json";

        try {
            resultado = objectMapper.readValue(new File(filename),Resultado.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public void salvarResultadoJogo(String tipo, String concurso){

        Resultado resultado = new Resultado();

        try {
             resultado = new ResultadoTask().execute(tipo,concurso).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salvarResultadosOffline(resultado,false);
    }


    public Resultado carregarResultadoOfflinebyFilename(String filename){

        ObjectMapper objectMapper = new ObjectMapper();
        Resultado resultado = new Resultado();

        try {
            resultado = objectMapper.readValue(new File(filename),Resultado.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;

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
