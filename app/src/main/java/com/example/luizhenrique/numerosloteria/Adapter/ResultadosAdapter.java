package com.example.luizhenrique.numerosloteria.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.R;

import java.util.List;

/**
 * Created by luizhenrique on 01/08/17.
 */

public class ResultadosAdapter extends BaseAdapter {

    Context ctx;
    List<Resultado> resultados;

     public ResultadosAdapter(Context ctx,List<Resultado> resultados){

         this.ctx = ctx;
         this.resultados = resultados;

     }
    @Override
    public int getCount() {
        return resultados.size();
    }

    @Override
    public Object getItem(int position) {
        return resultados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Resultado res = resultados.get(position);
        View linha = null;

        if (res != null) {

            linha = LayoutInflater.from(ctx).inflate(R.layout.item_resultado, null);

            String dataSorteio = res.getData();
            String data = dataSorteio.substring(8, 10) + "/" + dataSorteio.substring(5, 7) + "/" + dataSorteio.substring(0, 4);

            try {

                TextView tvResTipoJogo = linha.findViewById(R.id.tvResTipoJogo);
                TextView tvResSorteio = linha.findViewById(R.id.tvResNumSorteio);
                TextView tvResData = linha.findViewById(R.id.tvResData);
                GridLayout gridLayout = linha.findViewById(R.id.glResNumeros);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(160, 160);

                for (int i = 0; i < res.getSorteio().size(); i++) {

                    TextView t = new TextView(linha.getContext());
                    t.setText(String.valueOf(res.getSorteio().get(i)));
                    t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                    t.setLayoutParams(lp);
                    t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    t.setTextSize(18);
                    t.setTextColor(Color.WHITE);
                    t.setTypeface(Typeface.DEFAULT_BOLD);


                    gridLayout.addView(t);

                    switch (res.getTipo()) {
                        case "mega-sena":

                            t.setBackgroundResource(R.drawable.bolamega);
                            break;
                        case "lotomania":

                            t.setBackgroundResource(R.drawable.bolalotomania);
                            break;
                        case "lotofacil":

                            t.setBackgroundResource(R.drawable.bolalotofacil);
                            break;
                        case "quina":

                            t.setBackgroundResource(R.drawable.bolaquina);
                            break;
                        case "timemania":

                            t.setBackgroundResource(R.drawable.bolatimemania);
                            break;
                    }

                }

                tvResSorteio.setText(String.valueOf(res.getNumero()));
                tvResTipoJogo.setText(res.getTipo().toUpperCase());
                tvResData.setText(data);

                switch (res.getTipo()) {
                    case "mega-sena":
                        tvResTipoJogo.setTextColor(Color.parseColor("#0f5935"));
                        break;
                    case "lotomania":
                        tvResTipoJogo.setTextColor(Color.parseColor("#EC4526"));
                        break;
                    case "lotofacil":
                        tvResTipoJogo.setTextColor(Color.parseColor("purple"));
                        break;
                    case "quina":
                        tvResTipoJogo.setTextColor(Color.BLUE);
                        break;
                    case "timemania":
                        tvResTipoJogo.setTextColor(Color.parseColor("maroon"));
                        break;
                }

            } catch (Exception ex) {

            }

        }return linha;
    }
}
