package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Presenter.DetalhesJogoPresenter;
import com.example.luizhenrique.numerosloteria.Presenter.DetalhesJogoPresenterImpl;
import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;
import com.example.luizhenrique.numerosloteria.Services.RealmServices;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;
import com.example.luizhenrique.numerosloteria.View.DetalhesJogoView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class DetalhesJogo extends AppCompatActivity implements DetalhesJogoView {

    public TextView tvTipoJogo;
    public TextView tvSorteio;
    public TextView tvNumeros;
    public TextView tvAcertos;
    public TextView tvTxtAcertoData;
    public TextView tvValorPremio;
    public TextView tvTimedoCoracao;
    public TextView tvPremioTimeCoracao;
    public Toolbar toolbarDetalhes;
    Resultado res;
    RealmServices realmServices;
    int acertos = 0;
    Jogo jogo;
    public NumberFormat numberFormat;
    float premioTimeCoracao;

    public ArrayList<Integer> numerosAcertados;

    public static int corBola;

    DetalhesJogoPresenter detalhesJogoPresenter;
    HashMap <String,Integer> mapBolas;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_jogo);

        detalhesJogoPresenter = new DetalhesJogoPresenterImpl(this);

        realmServices = new RealmServices(this);

        mapBolas = new HashMap<>();

        mapBolas.put("Mega-Sena",R.drawable.bolamega);
        mapBolas.put("Dupla-Sena",R.drawable.boladuplasena);
        mapBolas.put("LotoFacil",R.drawable.bolalotofacil);
        mapBolas.put("Quina",R.drawable.bolaquina);
        mapBolas.put("LotoMania",R.drawable.bolalotomania);
        mapBolas.put("TimeMania",R.drawable.bolatimemania);

        tvTipoJogo = findViewById(R.id.tvTipoJogo);
        tvSorteio = findViewById(R.id.tvSorteio);
        tvNumeros = findViewById(R.id.tvNumeros);
        tvAcertos = findViewById(R.id.tvAcertos);
        tvValorPremio = findViewById(R.id.tvValorPremio);
        toolbarDetalhes = findViewById((R.id.toolbar4));
        tvTxtAcertoData = findViewById(R.id.tvTxtAcertoData);
        tvTimedoCoracao = findViewById(R.id.txtTimeCoracao);
        tvPremioTimeCoracao = findViewById(R.id.premioTimeCoracao);
        setSupportActionBar(toolbarDetalhes);

        numerosAcertados = new ArrayList<>();

        Locale locale;
        locale = Locale.forLanguageTag("pt-br");

        numberFormat = NumberFormat.getCurrencyInstance(locale);

        Intent it = getIntent();

        int id = it.getIntExtra("id", 0);

        jogo = new RealmServices(this).getJogo(id);

        try {
            res = new ResultadoTask().execute(jogo.tipoJogo, String.valueOf(jogo.sorteio)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        tvTipoJogo.setText(jogo.tipoJogo);
        tvSorteio.setText(String.valueOf(jogo.sorteio));

       corBola = mapBolas.get(jogo.tipoJogo);

        premioTimeCoracao = 0;

        switch (jogo.tipoJogo) {

            case "Mega-Sena":
                tvTipoJogo.setBackgroundColor(Color.parseColor("#0f5935"));
                toolbarDetalhes.setBackgroundColor(Color.parseColor("#0f5935"));
                break;

            case "LotoMania":
                tvTipoJogo.setBackgroundColor(Color.parseColor("#EC4526"));
                toolbarDetalhes.setBackgroundColor(Color.parseColor("#EC4526"));
                break;
            case "LotoFacil":
                tvTipoJogo.setBackgroundColor(Color.parseColor("purple"));
                toolbarDetalhes.setBackgroundColor(Color.parseColor("purple"));
                break;
            case "Quina":
                tvTipoJogo.setBackgroundColor(Color.BLUE);
                toolbarDetalhes.setBackgroundColor(Color.BLUE);
                break;
            case "TimeMania":
                tvTipoJogo.setBackgroundColor(Color.parseColor("maroon"));
                toolbarDetalhes.setBackgroundColor(Color.parseColor("maroon"));
                tvTimedoCoracao.setText(jogo.timeDoCoracao);
                tvTimedoCoracao.setVisibility(View.VISIBLE);
                break;
            case "Dupla-Sena":
                tvTipoJogo.setBackgroundColor(Color.parseColor("#af3869"));
                toolbarDetalhes.setBackgroundColor(Color.parseColor("#af3869"));
                break;
        }

        mostrarAposta();
        gerarGrid();

    }

    @Override
    public void carregarJogo() {

    }

    public void mostrarAposta() {

        try {

            if (res != null) {

                tvAcertos.setText(String.valueOf(detalhesJogoPresenter.verificarNumerosAcertos(res, jogo).size()));
                tvValorPremio.setText(String.valueOf("Você ganhou " + numberFormat.format(detalhesJogoPresenter.verificarPremiacao(res,jogo))));

                if (res.getTipo().equals("TimeMania")){
                    tvPremioTimeCoracao.setText("Time do Coração "+ numberFormat.format(detalhesJogoPresenter.verificarPremiacaoTime(res,jogo)));
                    tvPremioTimeCoracao.setVisibility(View.VISIBLE);
                }

            } else {

                detalhesJogoPresenter.getResultadoAnterior(jogo.tipoJogo,jogo.sorteio);
            }

        } catch (Exception ex) {

            Toast.makeText(DetalhesJogo.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void gerarGrid() {

        GridLayout tb = findViewById(R.id.glDetalhesJogoNumeros);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(160, 160);

        int[] nums = GeradorDeNumeros.ParseToInt(jogo);

        numerosAcertados = detalhesJogoPresenter.verificarNumerosAcertos(res,jogo);

        for (int num : nums) {

            TextView t = new TextView(this);
            t.setText(String.valueOf(num));
            t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            t.setLayoutParams(lp);
            t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            t.setTextSize(18);
            t.setTextColor(Color.WHITE);
            t.setTypeface(Typeface.DEFAULT_BOLD);

            if (numerosAcertados.size() > 0) {
                for (int n : numerosAcertados) {

                    if (n == num) {
                        t.setBackgroundResource(corBola);
                        break;
                    } else {
                        t.setBackgroundResource(R.drawable.bolanaoac);

                    }
                }
            } else {
                t.setBackgroundResource(R.drawable.bolanaoac);
            }
            tb.addView(t);
        }
    }

    @Override
    public void setTextView(String txt) {

        tvValorPremio.setText(txt);
        tvAcertos.setText("");
        tvTxtAcertoData.setText("");
    }

    public void EditarJogo(Jogo jogo){

        Intent it = new Intent(DetalhesJogo.this,AdicionarJogo.class);

        it.putExtra("id",jogo.id);

        startActivity(it);
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_detalhes_jogo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_Excluir:
                new RealmServices(this).ExcluirJogo(jogo);
                finish();
                return true;

            case R.id.action_Editar:
                EditarJogo(jogo);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}



