package com.example.luizhenrique.numerosloteria.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;

/**
 * Created by luizhenrique on 01/10/2017.
 */

public class ConsultaSorteioFragment extends DialogFragment {

    private Spinner spinnerJogo;
    private EditText etConcurso;
    private String tipoJogo;
    private String concurso;
    Resultado result = new Resultado();

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.fragment_consulta_sorteios,null);

        spinnerJogo = layout.findViewById(R.id.spinnerJogo);
        etConcurso = layout.findViewById(R.id.edConcurso);

        builder.setView(layout)


                .setPositiveButton("OK", null)
                .setNegativeButton("Cancelar", null);

        return builder.create();

    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tipoJogo = spinnerJogo.getSelectedItem().toString();
                concurso = etConcurso.getText().toString();

                result = new ResultadoService().carregarResultadoOffline(tipoJogo.toLowerCase());

                if (concurso.equals("")){

                    etConcurso.setError("Digite o número do concurso");

                }
                else if (Integer.valueOf(concurso) < result.getNumero() && verificarConexao()){
                    Intent it = new Intent(getActivity(),DetalhesSorteio.class);
                    it.putExtra("tipoJogo",tipoJogo);
                    it.putExtra("concurso", concurso);
                    it.putExtra("flagConsulta",true);
                    startActivity(it);
                    dismiss();
                }
                else if (!verificarConexao()){
                    etConcurso.setError("Você precisa estar conectado para pesquisar");


                }
                else {
                    etConcurso.setError("Concurso inválido");
                    etConcurso.setText("");
                }
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultaSorteioFragment.this.getDialog().cancel();
            }
        });
    }

    public boolean verificarConexao() {

        Context ctx = getContext();
        ConnectivityManager cm;
        NetworkInfo info;
        cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        info = cm.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }
}
