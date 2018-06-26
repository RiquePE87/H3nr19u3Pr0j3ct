package com.example.luizhenrique.numerosloteria.Model;

import java.io.Serializable;

/**
 * Created by luizhenrique on 28/07/17.
 */

public class Resultado implements Serializable {

    public String tipo;
    private int numero;
    private String data;
   // public int []numeroSorteio;
   private int[]sorteio;
    private int[] ganhadores;
    private float[] rateio;
    private String acumulado;
    private float valor_acumulado;
    //private String[] cidades;
    private int proximo_estimativa;
    private String proximo_data;
    private String time;
    public int Acertos;
    int i = 0;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int[] getSorteio() {
        return sorteio;
    }

    public void setSorteio(int[] sorteio) {
        this.sorteio = sorteio;
    }

    public int[] getGanhadores() {
        return ganhadores;
    }

    public void setGanhadores(int[] ganhadores) {
        this.ganhadores = ganhadores;
    }

    public float[] getRateio() {
        return rateio;
    }

    public void setRateio(float[] rateio) {
        this.rateio = rateio;
    }

    public String getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }

    public float getValor_acumulado() {
        return valor_acumulado;
    }

    public void setValor_acumulado(float valor_acumulado) {
        this.valor_acumulado = valor_acumulado;
    }

    public int getProximo_estimativa() {
        return proximo_estimativa;
    }

    public void setProximo_estimativa(int proximo_estimativa) {
        this.proximo_estimativa = proximo_estimativa;
    }

    public String getProximo_data() {
        return proximo_data;
    }

    public void setProximo_data(String proximo_data) {
        this.proximo_data = proximo_data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}