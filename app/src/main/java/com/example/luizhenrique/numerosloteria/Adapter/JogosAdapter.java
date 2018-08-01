package com.example.luizhenrique.numerosloteria.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;

import java.util.List;

/**
 * Created by luizhenrique on 25/07/17.
 */

public class JogosAdapter extends BaseAdapter {

    Context ctx;
    List<Jogo> jogos;

    public JogosAdapter(Context ctx, List<Jogo> jogos){

        this.ctx = ctx;
        this.jogos = jogos;
    }

    @Override
    public int getCount() {
        return jogos.size();
    }

    @Override
    public Object getItem(int position) {
        return jogos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            Jogo jogo = jogos.get(position);

            View linha = LayoutInflater.from(ctx).inflate(R.layout.item_jogo,null);

            try {

                TextView tvTipoJogo = linha.findViewById(R.id.tvTipoJogo);
                TextView tvSorteio = linha.findViewById(R.id.tvSorteio);
                CardView cardView = linha.findViewById(R.id.cardview);
                tvTipoJogo.setText(jogo.tipoJogo.toUpperCase());
                tvSorteio.setText(String.valueOf(jogo.sorteio));

                GridLayout tb = linha.findViewById(R.id.glNumeros);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(160,160);

                int[] nums = GeradorDeNumeros.ParseToInt(jogo);

                for (int i = 0;i<nums.length;i++){


                    TextView t = new TextView(linha.getContext());
                    t.setText(String.format("%01d",nums[i]));
                    t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                    t.setLayoutParams(lp);
                    t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    t.setTextSize(18);
                    t.setTextColor(Color.WHITE);

                    t.setTypeface(Typeface.DEFAULT_BOLD);
                    tb.addView(t);

                    switch (jogo.tipoJogo){
                        case "Mega-Sena":

                            t.setBackgroundResource(R.drawable.bolamega);
                            break;
                        case "LotoMania":

                            t.setBackgroundResource(R.drawable.bolalotomania);
                            break;
                        case "LotoFacil":

                            t.setBackgroundResource(R.drawable.bolalotofacil);
                            break;
                        case "Quina":

                            t.setBackgroundResource(R.drawable.bolaquina);
                            break;
                        case "TimeMania":

                            t.setBackgroundResource(R.drawable.bolatimemania);
                            break;

                        case "Dupla-Sena":
                            t.setBackgroundResource(R.drawable.boladuplasena);
                            break;

                        case "Dia-de-Sorte":
                            t.setBackgroundResource(R.drawable.boladiadesorte);
                            break;
                    }
                }


                switch (jogo.tipoJogo){
                    case "Mega-Sena":
                        tvTipoJogo.setTextColor(Color.parseColor("#0F5935"));
                        break;
                    case "LotoMania":
                        tvTipoJogo.setTextColor(Color.parseColor("#EC4526"));
                        break;
                    case "LotoFacil":
                        tvTipoJogo.setTextColor(Color.parseColor("purple"));
                        break;
                    case "Quina":
                        tvTipoJogo.setTextColor(Color.BLUE);
                        break;
                    case "TimeMania":
                        tvTipoJogo.setTextColor(Color.parseColor("maroon"));
                        break;
                    case "Dupla-Sena":
                        tvTipoJogo.setTextColor(Color.parseColor("#af3869"));
                        break;
                    case "Dia-de-Sorte":
                        tvTipoJogo.setTextColor(Color.parseColor("#d3b315"));
                }

            }catch (Exception ex){

                Log.println(Log.ERROR,"erro",ex.getMessage());
            }

        return linha;
    }
}
