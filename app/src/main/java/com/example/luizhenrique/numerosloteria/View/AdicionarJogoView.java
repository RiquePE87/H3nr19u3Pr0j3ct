package com.example.luizhenrique.numerosloteria.View;

import android.content.Intent;
import android.widget.Spinner;

import com.example.luizhenrique.numerosloteria.Model.Jogo;

public interface AdicionarJogoView {

    void exibirProgress(boolean exibir);

    void setSpinText(Spinner spin, String text);

    void exibirToast(String message);

    void exibirPublicidade();

    boolean isAtualizacao(Intent it);

    void setView();

    Jogo pegarDadosJogo();

     void gerarTipoJogoView();

     void setNumerosError();

     void setSorteioError();
}
