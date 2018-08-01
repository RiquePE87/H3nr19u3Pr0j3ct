package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.Presenter.DetalhesSorteioPresenterImpl;
import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.Services.ResultadoTask;
import com.example.luizhenrique.numerosloteria.View.DetalhesSorteioView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetalhesSorteio extends AppCompatActivity implements DetalhesSorteioView{

    Resultado resultado;
    TextView tvJogo;
    TextView tvSorteio;
    TextView tvAcumulou;
    TextView tvDataSorteio;
    TextView tvValor;
    TextView tvProximoSorteio;
    TextView tvValorProximo;
    TextView tvTime;
    TextView tvAnterior;
    TextView tvProximo;
    TextView tvMes;
    List<Resultado> listResultados;
    TableLayout tableDetalhes;
    int rows = 1;
    private static final String TAG = "DetalhesSorteio";
    private AdView mAdView;
    public int ultimoConcurso;
    private DetalhesSorteioPresenterImpl detalhesSorteioPresenter;
    NumberFormat numberFormat;
    TableRow.LayoutParams lp;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_sorteio);

        detalhesSorteioPresenter = new DetalhesSorteioPresenterImpl(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent it = getIntent();
        listResultados = new ArrayList<>();

        final String tipo = it.getStringExtra("tipoJogo").toLowerCase();
        final String concurso = it.getStringExtra("concurso");
        ultimoConcurso = it.getIntExtra("ultimoSorteio",0);

        if (concurso == null){
            carregarSorteio(tipo,"");
        }
        else{
            carregarSorteio(tipo,concurso);
        }

        tvAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    carregarSorteio(tipo,String.valueOf(resultado.getNumero()-1));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        tvProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    carregarSorteio(tipo,String.valueOf(resultado.getNumero()+1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  void carregarSorteio(String tp, String sort){

        try{

            if (sort == ""){
                resultado = new ResultadoTask().execute(tp).get();
            }
            else{
                resultado = new ResultadoTask().execute(tp,sort).get();

            }

        }catch (Exception ec){
            ec.printStackTrace();
        }

        Locale locale;
        locale = Locale.forLanguageTag("pt-br");

        numberFormat = NumberFormat.getCurrencyInstance(locale);

        String JogoMaiuscula = resultado.getTipo().toUpperCase();

        tvJogo = findViewById(R.id.tvDetJogo);
        tvSorteio = findViewById(R.id.tvDetSorteio);
        tvAcumulou = findViewById(R.id.tvDetAcuulou);
        tvDataSorteio = findViewById(R.id.tvDetDataSorteio);
        tvValor = findViewById(R.id.tvDetValor);
        tvProximoSorteio = findViewById(R.id.tvDetDataProxSorteio);
        tvValorProximo = findViewById(R.id.tvDetValorAcumulado);
        tvTime = findViewById(R.id.tvDetTime);
        tvAnterior = findViewById(R.id.tvAnterior);
        tvProximo = findViewById(R.id.tvProximo);
        tableDetalhes = findViewById(R.id.tblDetalhes);
        tvMes = findViewById(R.id.tvMes);

        if (resultado.getTipo().equals("dia-de-sorte")){
            tvMes.setText(resultado.getMes());
        }
        else {
            tvMes.setVisibility(View.GONE);
        }

        if (resultado.getNumero() == ultimoConcurso) {

            tvProximo.setVisibility(View.INVISIBLE);
        }
        else{
            tvProximo.setVisibility(View.VISIBLE);
        }

        String data = ResultadoService.formatarData(resultado.getData());

        String dataproximo = ResultadoService.formatarData(resultado.getProximoData());

        detalhesSorteioPresenter.preencherGanhadores(resultado);

        tvJogo.setText(JogoMaiuscula);
        tvSorteio.setText(String.valueOf(resultado.getNumero()));

        if (resultado.getAcumulado().equals("sim")){

            tvAcumulou.setText("Acumulou");
            tvValor.setText(numberFormat.format(resultado.getValorAcumulado()));
        }else{

            tvValor.setText(numberFormat.format(resultado.getRateio().get(0)));


            if (resultado.getGanhadores().get(0).equals(1)){
                tvAcumulou.setText(resultado.getGanhadores().get(0)+" Ganhador");
            }
            else{
                tvAcumulou.setText(resultado.getGanhadores().get(0)+" Ganhadores");
            }
            }

        tvDataSorteio.setText(data);
        gridLayout = findViewById(R.id.glDetNumeros);

        lp = new TableRow.LayoutParams(160,160);
        gridLayout.removeAllViews();

        if (resultado.getTipo().equals("dupla-sena")) {

            ArrayList<Integer> jogo1 = (ArrayList<Integer>) resultado.getSorteio().get(0);
            ArrayList<Integer> jogo2 = (ArrayList<Integer>) resultado.getSorteio().get(1);

            gerarBolas(jogo1);
            gerarBolas(jogo2);
        }
        else {

        for (int i = 0; i< resultado.getSorteio().size(); i++){

                TextView t = new TextView(this);
                t.setText(String.valueOf(resultado.getSorteio().get(i)));
                t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                t.setLayoutParams(lp);
                t.setBackgroundResource(R.drawable.bola);
                t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t.setTextSize(18);
                t.setTextColor(Color.WHITE);
                t.setTypeface(Typeface.DEFAULT_BOLD);
                gridLayout.addView(t);

                switch (resultado.getTipo()){

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
                    case "dia-de-sorte":

                        t.setBackgroundResource(R.drawable.boladiadesorte);
                        break;
                }
            }
        }

        tvProximoSorteio.setText(dataproximo);
        tvValorProximo.setText(numberFormat.format((resultado.getProximoEstimativa())));
        tvTime.setText(resultado.getTime());
        tvTime.setVisibility(View.VISIBLE);

        switch (resultado.getTipo()){

            case "mega-sena":
                tvJogo.setBackgroundColor(Color.parseColor("#FF0F4B11"));
                break;
            case "lotomania":
                tvJogo.setBackgroundColor(Color.parseColor("#EC4526"));
                break;
            case "lotofacil":
                tvJogo.setBackgroundColor(Color.parseColor("purple"));
                break;
            case "quina":
                tvJogo.setBackgroundColor(Color.BLUE);
                break;
            case "timemania":
                tvJogo.setBackgroundColor(Color.parseColor("maroon"));
                break;
            case "dupla-sena":
                tvJogo.setBackgroundColor(Color.parseColor("#af3869"));
                break;
            case "dia-de-sorte":
                tvJogo.setBackgroundColor(Color.parseColor("#d3b315"));
                break;
        }

    }

    public void removerLinhas(){

        if (tableDetalhes.getChildCount() > 0){
            tableDetalhes.removeViews(1,tableDetalhes.getChildCount()-1);
        }
    }

    public void inserirLinha(String acertos, String ganhadores, Object premio, int rows, boolean flagJogo){

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        if (flagJogo == false){
            row.setBackgroundColor(Color.WHITE);
        }
        else {
            row.setBackgroundColor(Color.LTGRAY);
        }

        TextView txtAcertos = new TextView(this);
        TextView txtGanhador = new TextView(this);
        TextView txtPremio = new TextView(this);

        txtAcertos.setText(String.valueOf(acertos));
        txtGanhador.setText(String.valueOf(ganhadores));
        txtPremio.setText(String.valueOf(numberFormat.format((premio))));

        txtAcertos.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        txtGanhador.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        txtPremio.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        row.addView(txtAcertos);
        row.addView(txtGanhador);
        row.addView(txtPremio);

        tableDetalhes.addView(row,rows);
    }

    public void gerarBolas(ArrayList<Integer> jogo){

        for (int i = 0; i < 6; i++) {

            TextView t = new TextView(this);
            t.setText(String.valueOf(jogo.get(i)));
            t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            t.setLayoutParams(lp);
            t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            t.setTextSize(18);
            t.setTextColor(Color.WHITE);
            t.setTypeface(Typeface.DEFAULT_BOLD);
            t.setBackgroundResource(R.drawable.boladuplasena);

            gridLayout.addView(t);

        }
    }
}
