package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Services.JogoManager;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.View.DetalhesSorteioView;

public class DetalhesSorteioPresenterImpl implements DetalhesSorteioPresenter {

    private DetalhesSorteioView detalhesSorteioView;

    public DetalhesSorteioPresenterImpl(DetalhesSorteioView detalhesSorteioView){

        this.detalhesSorteioView = detalhesSorteioView;
    }

    public void preencherGanhadores(Resultado res) {

        detalhesSorteioView.removerLinhas();
        int[] acertos = new JogoManager().getAcertos(res.getTipo().toLowerCase());
        String txt;
        int rows = 1;

        if (detalhesSorteioView != null) {
            for (int i = 0; i < res.getGanhadores().size();i++){

                if (res.getTipo().equals("timemania") && acertos[i] == 2){

                    detalhesSorteioView.inserirLinha("Time do Coração ", String.valueOf(res.getGanhadores().get(i)),res.getRateio().get(i),rows);

                }else{
                    detalhesSorteioView.inserirLinha(String.valueOf(acertos[i]), String.valueOf(res.getGanhadores().get(i)),res.getRateio().get(i),rows);
                    rows++;
                }
            }
        }
    }
}
