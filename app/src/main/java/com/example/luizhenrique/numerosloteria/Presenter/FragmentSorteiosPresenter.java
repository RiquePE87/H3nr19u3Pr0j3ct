package com.example.luizhenrique.numerosloteria.Presenter;

import com.example.luizhenrique.numerosloteria.Adapter.ResultadosAdapter;
import com.example.luizhenrique.numerosloteria.Model.Resultado;

import java.util.List;

public interface FragmentSorteiosPresenter {

     List<Resultado> carregarResultados();

     ResultadosAdapter setSorteioAdapter();

     boolean verificarConexao();
}
