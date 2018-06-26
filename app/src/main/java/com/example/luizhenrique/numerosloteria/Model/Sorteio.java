package com.example.luizhenrique.numerosloteria.Model;

import java.util.List;

public class Sorteio {

    private String numero;
    private String data;
    private List<List<Integer>> sorteio;
    private List<List<Integer>> ganhadores;
    private List<List<Integer>> rateio;
    private String acumulado;
    private String valor_acumulado;
    private List<String> cidades;
    private int proximo_estimativa;
    private String proximo_data;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<List<Integer>> getSorteio() {
        return sorteio;
    }

    public void setSorteio(List<List<Integer>> sorteio) {
        this.sorteio = sorteio;
    }

    public List<List<Integer>> getGanhadores() {
        return ganhadores;
    }

    public void setGanhadores(List<List<Integer>> ganhadores) {
        this.ganhadores = ganhadores;
    }

    public List<List<Integer>> getRateio() {
        return rateio;
    }

    public void setRateio(List<List<Integer>> rateio) {
        this.rateio = rateio;
    }

    public String getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }

    public String getValor_acumulado() {
        return valor_acumulado;
    }

    public void setValor_acumulado(String valor_acumulado) {
        this.valor_acumulado = valor_acumulado;
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
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
}
