package com.example.luizhenrique.numerosloteria.Services;

import android.os.AsyncTask;

import com.example.luizhenrique.numerosloteria.Model.Resultado;

public class ResultadoTask extends AsyncTask<String, Void, Resultado> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // FragmentSorteios.fragment.exibirProgress(true);
    }

    @Override
    protected Resultado doInBackground(String... params) {

        try {
            if (params.length == 2) {
                return ResultadoService.carregarResultado(params[0], params[1]);
            } else {
                return ResultadoService.carregarResultado(params[0]);
            }
        }catch (Exception ex){

        }

        return null;
    }

    @Override
    protected void onPostExecute(Resultado resultado) {
        super.onPostExecute(resultado);
       // FragmentSorteios.fragment.exibirProgress(false);
    }
}
