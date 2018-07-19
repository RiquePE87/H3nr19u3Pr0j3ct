package com.example.luizhenrique.numerosloteria.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sorteio implements Serializable {

    @JsonIgnoreProperties(value = "cidades",ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "numero",
            "data",
            "sorteio",
            "ganhadores",
            "rateio",
            "acumulado",
            "valor_acumulado",
//            "cidades",
            "proximo_estimativa",
            "proximo_data"
    })

        @JsonProperty("numero")
        private Integer numero;
        @JsonProperty("data")
        private String data;
        @JsonProperty("sorteio")
        private List<Object> sorteio = null;
        @JsonProperty("ganhadores")
        private List<Object> ganhadores = null;
        @JsonProperty("rateio")
        private List<Object> rateio = null;
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
        private String tipo;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("numero")
        public Integer getNumero() {
            return numero;
        }

        @JsonProperty("numero")
        public void setNumero(Integer numero) {
            this.numero = numero;
        }

        @JsonProperty("data")
        public String getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(String data) {
            this.data = data;
        }

        @JsonProperty("sorteio")
        public List<Object> getSorteio() {
            return sorteio;
        }

        @JsonProperty("sorteio")
        public void setSorteio(List<Object> sorteio) {
            this.sorteio = sorteio;
        }

        @JsonProperty("ganhadores")
        public List<Object> getGanhadores() {
            return ganhadores;
        }

        @JsonProperty("ganhadores")
        public void setGanhadores(List<Object> ganhadores) {
            this.ganhadores = ganhadores;
        }

        @JsonProperty("rateio")
        public List<Object> getRateio() {
            return rateio;
        }

        @JsonProperty("rateio")
        public void setRateio(List<Object> rateio) {
            this.rateio = rateio;
        }

        @JsonProperty("acumulado")
        public String getAcumulado() {
            return acumulado;
        }

        @JsonProperty("acumulado")
        public void setAcumulado(String acumulado) {
            this.acumulado = acumulado;
        }

        @JsonProperty("valor_acumulado")
        public Double getValorAcumulado() {
            return valorAcumulado;
        }

        @JsonProperty("valor_acumulado")
        public void setValorAcumulado(Double valorAcumulado) {
            this.valorAcumulado = valorAcumulado;
        }

        @JsonProperty("cidades")
        public List<Object> getCidades() {
            return cidades;
        }

        @JsonProperty("cidades")
        public void setCidades(List<Object> cidades) {
            this.cidades = cidades;
        }

        @JsonProperty("proximo_estimativa")
        public Integer getProximoEstimativa() {
            return proximoEstimativa;
        }

        @JsonProperty("proximo_estimativa")
        public void setProximoEstimativa(Integer proximoEstimativa) {
            this.proximoEstimativa = proximoEstimativa;
        }

        @JsonProperty("proximo_data")
        public String getProximoData() {
            return proximoData;
        }

        @JsonProperty("proximo_data")
        public void setProximoData(String proximoData) {
            this.proximoData = proximoData;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
