package com.example.luizhenrique.numerosloteria.Services;

import com.example.luizhenrique.numerosloteria.Model.Jogo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;


/**
 * Created by luizhenrique on 23/07/17.
 */

public class GeradorDeNumeros {

    public static int[] gerarNumeros(int quantidadeNumeros, int rangeNumeros) {

        int[] numeros = new int[quantidadeNumeros];
        Random rdm = new Random();


        if (rangeNumeros == 100) {
            for (int i = 0; i < numeros.length; i++) {

                numeros[i] = rdm.nextInt(rangeNumeros);
            }

            Arrays.sort(numeros);
        } else {
            for (int i = 0; i < numeros.length; i++) {

                numeros[i] = rdm.nextInt(rangeNumeros)+1;
            }
            Arrays.sort(numeros);
        }

        return checarNumerosIguais(numeros, rangeNumeros);
    }

    public static int[] checarNumerosIguais(int[] num, int rangeNumeros) {

        int temp = num[0];
        Random rdm = new Random();


        for (int a = 1; a < num.length; a++) {

            if (num[a] == temp) {

                if (rangeNumeros == 100) {
                    num[a] = rdm.nextInt(rangeNumeros);
                    a = 0;
                    Arrays.sort(num);
                    temp = num[0];
                } else {
                    num[a] = rdm.nextInt(rangeNumeros)+1;
                    a = 0;
                    Arrays.sort(num);
                    temp = num[0];
                }

            } else {
                temp = num[a];
            }
        }
        return num;
    }

    public static int[] ParseToInt(String numeros, int dezenas){

        int[] inteiros = new int[dezenas];
        String num = "";
        int count = 0;

        for (int i = 0; i < numeros.length(); i++)
        {
            if (!Character.isWhitespace(numeros.charAt(i)))
            {
                num += numeros.charAt(i);
            }
            else
            {
                inteiros[count] += Integer.parseInt(num);
                count++;
                num = "";
            }
        }

        return inteiros;
    }

    public static int[] ParseToInt(Jogo jogo){

        String numeros = jogo.numeros;
        int[] inteiros = new int[jogo.NumeroDezenas];
        String num = "";
        int count = 0;

        for (int i = 0; i < numeros.length(); i++)
        {
            if (!Character.isWhitespace(numeros.charAt(i)))
            {
                num += numeros.charAt(i);
            }
            else
            {
                inteiros[count] += Integer.parseInt(num);
                count++;
                num = "";
            }
        }

        return inteiros;
    }

    public static String ParseToString(int[] nums) {

        String str = "";

        for (int i = 0; i < nums.length; i++) {
            str += nums[i] + " ";
        }

        return str;
    }

    private static Map<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Entry<Integer, Integer>> list =
                new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    public  static int[] gerarMaisSorteadas(int[] nums, int dezenas, int quantidadeNumeros){

        int contador = 0;
        int i;
        Map<Integer,Integer> dic = new LinkedHashMap<>();

        for (int n : nums) {

            for (i = 1; i < nums.length; i++) {

                if (n == nums[i - 1]) {

                    contador++;

                    if (dic.containsKey(n)){

                        dic.put(n,contador);
                    }
                    else{
                        dic.put(n,contador);
                    }
                }
                else{
                    contador = 0;
                }
            }
        }

        Map<Integer, Integer> sortedMap;

        sortedMap = sortByValue(dic);

        Set<Map.Entry<Integer, Integer>> set = sortedMap.entrySet();

        Iterator<Map.Entry<Integer, Integer>> it = set.iterator();

        int[] maioresValores = new int[sortedMap.size()];

        int c= 0;

        while (it.hasNext()){

            Map.Entry<Integer, Integer> me = it.next();
            maioresValores[c] = Integer.valueOf(String.valueOf(me.getKey()));
            c++;
        }

        int[] maisSorteados = new int[dezenas];
        int o = 0;

        for (int v=maioresValores.length-dezenas;v<maioresValores.length;v++){

            maisSorteados[o] = maioresValores[v];
            o++;
        }
        return maisSorteados;
    }
}

