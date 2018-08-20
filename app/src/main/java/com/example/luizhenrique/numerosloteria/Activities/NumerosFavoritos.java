package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;

public class NumerosFavoritos extends AppCompatActivity {

    FloatingActionButton fab_adicionar;
    int[] numeroDezenas;
    String numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros_favoritos);

        fab_adicionar = findViewById(R.id.fab_adc_numero);
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

        fab_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(NumerosFavoritos.this,SelecaoNumeros.class);
                startActivity(it);
            }
        });

        if (sharedPreferences.contains("meus_numeros_favoritos")) {

            for (int i = 0; i < numeroDezenas.length; i++) {

                TextView t = new TextView(this);
                t.setText(String.valueOf(numeroDezenas[i]));
                t.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                t.setLayoutParams(lp);
                t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t.setTextSize(18);
                t.setTextColor(Color.DKGRAY);
                t.setBackgroundResource(R.drawable.bola);
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //NumeroClicado(v);
                    }
                });
                gridLayoutMeusNumeros.addView(t);
            }
        }
    }
}
