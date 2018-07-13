package com.example.luizhenrique.numerosloteria.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"cidades"})
public class Sorteio {

    @JsonProperty("numero")
    private Integer numero;
    @JsonProperty("data")
    private String data;
    @JsonProperty("sorteio")
    private List<List<Integer>> sorteio = null;
    @JsonProperty("ganhadores")
    private List<List<Integer>> ganhadores = null;
    @JsonProperty("rateio")
    private List<List<Integer>> rateio = null;
    @JsonProperty("acumulado")
    private String acumulado;
    @JsonProperty("valor_acumulado")
    private Double valorAcumulado;
    @JsonProperty("cidades")
    private List<Object> cidades = null;
    @JsonProperty("proximo_estimativa")
    private Integer proximoEstimativa;
    @JsonProperty("proximo_data")
    private String proximoData;

    public Sorteio(Integer numero, String data, List<List<Integer>> sorteio, List<List<Integer>> ganhadores, List<List<Integer>> rateio, String acumulado, Double valorAcumulado, List<Object> cidades, Integer proximoEstimativa, String proximoData) {
        this.numero = numero;
        this.data = data;
        this.sorteio = sorteio;
        this.ganhadores = ganhadores;
        this.rateio = rateio;
        this.acumulado = acumulado;
        this.valorAcumulado = valorAcumulado;
        //this.cidades = cidades;
        this.proximoEstimativa = proximoEstimativa;
        this.proximoData = proximoData;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public Double getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(Double valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public List<Object> getCidades() {
        return cidades;
    }

    public void setCidades(List<Object> cidades) {
        this.cidades = cidades;
    }

    public Integer getProximoEstimativa() {
        return proximoEstimativa;
    }

    public void setProximoEstimativa(Integer proximoEstimativa) {
        this.proximoEstimativa = proximoEstimativa;
    }

    public String getProximoData() {
        return proximoData;
    }

    public void setProximoData(String proximoData) {
        this.proximoData = proximoData;
    }
}
