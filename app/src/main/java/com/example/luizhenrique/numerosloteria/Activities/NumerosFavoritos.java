package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;

import java.util.ArrayList;

public class NumerosFavoritos extends AppCompatActivity {

    ArrayList<Integer> numerosSelecionados;
    FloatingActionButton fab_adicionar;
    android.support.v7.widget.Toolbar toolbarFavoritos;
    Button btnGerarNumeros;
    Button btnFavoritos;
    TextView tvNumeros;
    TextView tvNumerosFav;
    LinearLayout linearLayout;
    int[] numeroDezenas;
    int[] numerosJogo;
    String numeros;
    String numerosGerados;
    String tipoJogo;
    int rangeJogo;
    int dezenas;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros_favoritos);

        it = getIntent();

        numerosSelecionados = new ArrayList<>();

        tipoJogo = it.getStringExtra("jogo");
        rangeJogo = it.getIntExtra("numeroBolas",0);
        dezenas = it.getIntExtra("dezenas",0);

        //fab_adicionar = findViewById(R.id.fab_adc_numero);
        btnGerarNumeros = findViewById(R.id.btnGerarNumeros);
        tvNumeros = findViewById(R.id.numerosSelecionados);
        tvNumerosFav = findViewById(R.id.tvNumerosFav);
        toolbarFavoritos = findViewById(R.id.toolbar_favoritos);
        linearLayout = findViewById(R.id.linearFavoritos);
        btnFavoritos = findViewById(R.id.btFavoritos);

        setSupportActionBar(toolbarFavoritos);

        if (it.getExtras() == null){

            linearLayout.setVisibility(View.GONE);
            btnGerarNumeros.setVisibility(View.GONE);
            toolbarFavoritos.setTitle("Meus Números Favoritos");
            tvNumerosFav.setVisibility(View.GONE);
        }

        GridLayout gridLayoutMeusNumeros = findViewById(R.id.gridNumerosFavoritos);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(160,160);

        SharedPreferences sharedPreferences = getSharedPreferences("app",MODE_PRIVATE);

        if (sharedPreferences.contains("meus_numeros_favoritos")){
            numeros = sharedPreferences.getString("meus_numeros_favoritos","");
            int dezenas = sharedPreferences.getInt("dezenas", 0);
            numeroDezenas = GeradorDeNumeros.ParseToInt(numeros,dezenas);
        }
        else{
            numeroDezenas = new int[1];
        }

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(NumerosFavoritos.this,SelecaoNumeros.class);
                Intent intent = getIntent();
                it.putExtra("Intent",intent);
                startActivity(it);
            }
        });

        if (sharedPreferences.contains("meus_numeros_favoritos")) {

            if (it.getExtras() != null){

                for (int i = 0; i < numeroDezenas.length; i++) {

                    if (numeroDezenas[i] < rangeJogo){
                        TextView t = new TextView(this);
                        t.setText(String.valueOf(numeroDezenas[i]));
                        t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                        t.setLayoutParams(lp);
                        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        t.setTextSize(18);
                        t.setBackgroundResource(R.drawable.bola);
                        t.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NumeroClicado(v);
                            }
                        });
                        gridLayoutMeusNumeros.addView(t);
                    }
                }
            }else {

                for (int i = 0; i < numeroDezenas.length; i++) {

                    TextView t = new TextView(this);
                    t.setText(String.valueOf(numeroDezenas[i]));
                    t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                    t.setLayoutParams(lp);
                    t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    t.setTextSize(18);
                    t.setBackgroundResource(R.drawable.bola);
                    t.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NumeroClicado(v);
                        }
                    });
                    gridLayoutMeusNumeros.addView(t);
                }
            }
        }

        btnGerarNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                numerosJogo = new int[numerosSelecionados.size()];

                for (int n: numerosSelecionados){

                    numerosJogo[count] = n;
                    count++;
                }

                numerosGerados = GeradorDeNumeros.ParseToString(GeradorDeNumeros.gerarNumerosByFavoritos(numerosJogo,dezenas,rangeJogo));

                tvNumeros.setText(numerosGerados);
            }
        });
    }

    public void NumeroClicado(View view){

        TextView texto = (TextView) view;
        int corDefault = ((TextView) view).getCurrentTextColor();
        int count = 0;

        if (count < dezenas) {

            if (corDefault != Color.WHITE){

                numerosSelecionados.add(Integer.valueOf(String.valueOf(texto.getText())));
                view.setBackgroundResource(R.drawable.bolaselecionada);
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setTypeface(null, Typeface.BOLD);
                count++;
                //toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));

            }else{
                count--;
                numerosSelecionados.remove(Integer.valueOf(String.valueOf(texto.getText())));
                //toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
                view.setBackgroundResource(R.drawable.bola);
                ((TextView) view).setTextColor(Color.BLACK);
                ((TextView) view).setTypeface(null,Typeface.NORMAL);
            }
        }

        if (count == dezenas && corDefault == Color.WHITE){

            count--;
            //toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
            ((TextView) view).setTextColor(Color.BLACK);
            view.setBackgroundResource(R.drawable.bola);
            ((TextView) view).setTypeface(null,Typeface.NORMAL);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbarfavoritos,menu);

        if (it.getExtras() == null){

            menu.removeItem(R.id.action_OKFavoritos);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_OKFavoritos:

                    try {
                        Intent itResult = new Intent();
                        itResult.putExtra("numerosFavoritos", numerosGerados);
                        setResult(RESULT_OK,itResult);
                        finish();
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        return super.onOptionsItemSelected(item);
    }
}

