package com.example.luizhenrique.numerosloteria.Services;

import java.util.HashMap;

public class JogoManager {

    public static String[] megasena = { "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
    public static String[] duplasena = { "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
    public static String[] lotofacil = { "15", "16", "17", "18" };
    public static String[] quina = { "5", "6", "7" };
    public static String[] lotomania = { "50" };
    public static String[] timemania = {"10"};
    public static String[] diadesorte = {"7", "8", "9", "10", "11", "12", "13", "14", "15"};

    private HashMap<String,String[]> dicTipoJogos;

    private  HashMap<String,Integer> dicRangeJogos;

    private HashMap<String,Integer> dicRangeSorteios;

    private static int rangeMegaSena = 60;
    private static int rangeDuplaSena = 50;
    private static int rangeLotomania = 100;
    private static int rangeLotoFacil = 25;
    private static int rangeQuina = 80;
    private static int rangeTimeMania = 80;
    private static int rangeDiaDeSorte = 31;

    private static int rangeSorteioMegaSena = 6;
    private static int rangeSorteioDuplaSena = 6;
    private static int rangeSorteioLotoMania = 20;
    private static int rangeSorteioLotoFacil = 15;
    private static int rangeSorteioQuina = 5;
    private static int rangeSorteioTimeMania = 7;
    private static int rangeSorteioDiadeSorte = 7;

    private static int[] acertosMegaSena = {6,5,4};
    private  static int[] acertosDuplaSena = {6,5,4,3};
    private static int[] acertosLotoFacil = {15,14,13,12,11};
    private static int[] acertosLotoMania = {20,19,18,17,16,15,0};
    private static int[] acertosQuina = {5,4,3,2};
    private static int[] acertosTimeMania = {7,6,5,4,3,2};
    private static int[] acertosDiaDeSorte = {7,6,5,4,3};


    private HashMap<String,int[]> dicAcertos;

    public JogoManager(){

        dicRangeJogos = new HashMap<>();
        dicTipoJogos = new HashMap<>();
        dicAcertos = new HashMap<>();
        dicRangeSorteios = new HashMap<>();

        dicTipoJogos.put("mega-sena",megasena);
        dicTipoJogos.put("dupla-sena",duplasena);
        dicTipoJogos.put("lotofacil",lotofacil);
        dicTipoJogos.put("quina",quina);
        dicTipoJogos.put("lotomania",lotomania);
        dicTipoJogos.put("timemania",timemania);
        dicTipoJogos.put("dia-de-sorte",diadesorte);

        dicRangeJogos.put("mega-sena",rangeMegaSena);
        dicRangeJogos.put("dupla-sena",rangeDuplaSena);
        dicRangeJogos.put("lotomania",rangeLotomania);
        dicRangeJogos.put("lotofacil",rangeLotoFacil);
        dicRangeJogos.put("quina",rangeQuina);
        dicRangeJogos.put("timemania",rangeTimeMania);
        dicRangeJogos.put("dia-de-sorte",rangeDiaDeSorte);

        dicAcertos.put("mega-sena",acertosMegaSena);
        dicAcertos.put("dupla-sena", acertosDuplaSena);
        dicAcertos.put("lotomania", acertosLotoMania);
        dicAcertos.put("lotofacil", acertosLotoFacil);
        dicAcertos.put("quina", acertosQuina);
        dicAcertos.put("timemania", acertosTimeMania);
        dicAcertos.put("dia-de-sorte",acertosDiaDeSorte);

        dicRangeSorteios.put("mega-sena",rangeSorteioMegaSena);
        dicRangeSorteios.put("dupla-sena",rangeSorteioDuplaSena);
        dicRangeSorteios.put("lotomania",rangeSorteioLotoMania);
        dicRangeSorteios.put("lotofacil",rangeSorteioLotoFacil);
        dicRangeSorteios.put("quina",rangeSorteioQuina);
        dicRangeSorteios.put("timemania",rangeSorteioTimeMania);
        dicRangeSorteios.put("dia-de-sorte",rangeSorteioMegaSena);

    }

    public int getRangeSorteio(String tipoJogo){

        int range = dicRangeSorteios.get(tipoJogo);

        return range;
    }

    public String[] getTipoJogoNumeros(String tipoJogo){

        String[] numerosTipoJogo = dicTipoJogos.get(tipoJogo);

        return numerosTipoJogo;
    }

    public int getRangeJogo(String tipoJogo){

        int rangeJogo = dicRangeJogos.get(tipoJogo);

        return rangeJogo;
    }

    public int[] getAcertos(String tipoJogo){

        int[] acertosJogo = dicAcertos.get(tipoJogo);

        return acertosJogo;
    }
}
