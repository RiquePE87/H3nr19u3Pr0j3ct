package com.example.luizhenrique.numerosloteria.Model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by luizhenrique on 16/07/17.
 */

public class Jogo extends RealmObject implements Serializable {

    public int id;
    public String tipoJogo;
    public String numeros;
    public String numeros1;
    public int sorteio;
    public int NumeroDezenas;
    public String timeDoCoracao;



}
