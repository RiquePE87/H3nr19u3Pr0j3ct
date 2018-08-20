package com.example.luizhenrique.numerosloteria.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;

import java.util.ArrayList;
import java.util.Arrays;

public class SelecaoNumeros extends AppCompatActivity {

    int[] numeros;
    ArrayList<Integer> numerosFavoritos;
    int dezenas;
    int count = 0;
    int numeroDezenas;
    int i = 1;
    Toolbar toolbarSelecao;
    GridLayout gl;
    Intent it;
    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        it = getIntent();
        setContentView(R.layout.selecao_numeros);
        toolbarSelecao = findViewById(R.id.toolbar_selecao);
        toolbarSelecao.setTitle("");
        gl = findViewById(R.id.gridSelecao);
        setSupportActionBar(toolbarSelecao);
        sharedPref = getSharedPreferences("app",MODE_PRIVATE);
         editor = sharedPref.edit();

        TableRow.LayoutParams lp = new TableRow.LayoutParams(160,160);
        String jogo = it.getStringExtra("jogo");
        dezenas = it.getIntExtra("dezenas",0);
        numeroDezenas = it.getIntExtra("numeroBolas",0);

        if (it.getExtras() == null){

            numeroDezenas = 99;
            dezenas = 99;
            numerosFavoritos = new ArrayList<>();
            numeros = GeradorDeNumeros.ParseToInt(sharedPref.getString("meus_numeros_favoritos",""),sharedPref.getInt("dezenas",0));

            for (i = 1; i <= numeroDezenas; i++) {

                TextView t = new TextView(this);
                t.setText(String.valueOf(i));
                t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                t.setLayoutParams(lp);
                t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t.setTextSize(18);

                for (int n : numeros){

                    if (n == i){
                        t.setBackgroundResource(R.drawable.bolaselecionada);
                        ((TextView) t).setTextColor(Color.BLACK);
                        ((TextView) t).setTypeface(null, Typeface.BOLD);
                    }
                    else {
                        t.setTextColor(Color.DKGRAY);
                        t.setBackgroundResource(R.drawable.bola);
                    }
                }

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NumeroClicadoFavorito(v);
                    }
                });
                gl.addView(t);
            }


        }
        else {
            numeros = new int[dezenas];

            if (jogo.equals("LotoMania")){
                for (i = 0;i<numeroDezenas;i++) {

                    TextView t = new TextView(this);
                    t.setText(String.valueOf(i));
                    t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                    t.setLayoutParams(lp);
                    t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    t.setTextSize(18);
                    t.setTextColor(Color.DKGRAY);
                    t.setBackgroundResource(R.drawable.bola);
                    t.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NumeroClicado(v);
                        }
                    });
                    gl.addView(t);
                }
            }
            else {

                for (i = 1; i <= numeroDezenas; i++) {

                    TextView t = new TextView(this);
                    t.setText(String.valueOf(i));
                    t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                    t.setLayoutParams(lp);
                    t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    t.setTextSize(18);
                    t.setTextColor(Color.DKGRAY);
                    t.setBackgroundResource(R.drawable.bola);
                    t.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NumeroClicado(v);
                        }
                    });
                    gl.addView(t);
                }
            }
        }
    }

    public void NumeroClicado(View view){

        TextView texto = (TextView) view;
        int corDefault = ((TextView) view).getCurrentTextColor();

        if (count < dezenas) {

            if (corDefault != Color.BLACK){

                numeros[count] = Integer.valueOf(String.valueOf(texto.getText()));
                view.setBackgroundResource(R.drawable.bolaselecionada);
                ((TextView) view).setTextColor(Color.BLACK);
                ((TextView) view).setTypeface(null, Typeface.BOLD);
                count++;
                toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));

            }else{
                count--;
                toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
                view.setBackgroundResource(R.drawable.bola);
                ((TextView) view).setTextColor(Color.DKGRAY);
                ((TextView) view).setTypeface(null,Typeface.NORMAL);
            }
        }

        if (count == dezenas && corDefault == Color.BLACK){

            count--;
            toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
            ((TextView) view).setTextColor(Color.DKGRAY);
            view.setBackgroundResource(R.drawable.bola);
            ((TextView) view).setTypeface(null,Typeface.NORMAL);
        }
    }

    public void NumeroClicadoFavorito(View view){

        TextView texto = (TextView) view;
        int corDefault = ((TextView) view).getCurrentTextColor();



            if (corDefault != Color.BLACK){

                numerosFavoritos.add(Integer.valueOf(String.valueOf(texto.getText())));
                view.setBackgroundResource(R.drawable.bolaselecionada);
                ((TextView) view).setTextColor(Color.BLACK);
                ((TextView) view).setTypeface(null, Typeface.BOLD);
                count++;
                toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));

            }else{
                count--;
                numerosFavoritos.remove(Integer.valueOf((String) ((TextView) view).getText()));
                toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
                view.setBackgroundResource(R.drawable.bola);
                ((TextView) view).setTextColor(Color.DKGRAY);
                ((TextView) view).setTypeface(null,Typeface.NORMAL);
            }


//        if (count == dezenas && corDefault == Color.BLACK){
//
//            count--;
//            toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
//            ((TextView) view).setTextColor(Color.DKGRAY);
//            view.setBackgroundResource(R.drawable.bola);
//            ((TextView) view).setTypeface(null,Typeface.NORMAL);
//        }
    }

    public void limparDezenas(){

        for (int i = 0;i < gl.getChildCount();i++){

            TextView child = (TextView) gl.getChildAt(i);

            if (child.getCurrentTextColor() == Color.BLACK){

                child.setBackgroundResource(R.drawable.bola);
                child.setBackgroundResource(R.drawable.bola);
                child.setTextColor(Color.DKGRAY);
                child.setTypeface(null,Typeface.NORMAL);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_selecao,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_OKSelecao:

                if (it.getExtras() == null){

                    numeros = new int[count];

                    int count = 0;
                    for (int n : numerosFavoritos){

                        numeros[count] = n;
                        count++;
                    }
                    Arrays.sort(numeros);
                    String numsSelec = GeradorDeNumeros.ParseToString(numeros);
                    editor.putString("meus_numeros_favoritos",numsSelec);
                    editor.putInt("dezenas",count);
                    editor.apply();
                    finish();

                }else if (count == dezenas){
                    Intent itResult = new Intent();
                    Arrays.sort(numeros);
                    String numsSelec = GeradorDeNumeros.ParseToString(numeros);
                    itResult.putExtra("numerosSelec",numsSelec);
                    setResult(RESULT_OK,itResult);
                    finish();
                    return true;
                }else{
                    Toast.makeText(this,"Selecione o restante das dezenas",Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.action_limpar:
                limparDezenas();
                numeros = new int[dezenas];
                count = 0;
                toolbarSelecao.setTitle("Dezenas: "+String.valueOf(count));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.action_OKSelecao);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }
}
