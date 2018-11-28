package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luizhenrique.numerosloteria.BuildConfig;
import com.example.luizhenrique.numerosloteria.R;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActicity extends AppCompatActivity {

    TextView tvVersao;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean isFirstRun = true;
    Boolean isRated = false;
    int contadorUso;
    Set<String> jogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testando);

        sharedPreferences = getSharedPreferences("app",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        tvVersao = findViewById(R.id.tvVersao);
        tvVersao.setText("Versão "+BuildConfig.VERSION_NAME);

        if (!sharedPreferences.contains("jogos")){

            contadorUso = 0;
            jogos = new HashSet<>();

            jogos.add("Mega-Sena");
            jogos.add("LotoFacil");
            jogos.add("LotoMania");
            jogos.add("Quina");
            jogos.add("Dupla-Sena");
            jogos.add("TimeMania");
            jogos.add("Dia-de-Sorte");

            editor.putStringSet("jogos",jogos);
            editor.putBoolean("isRated", isRated);
            editor.putBoolean("isFirstRun", isFirstRun);
            editor.putInt("contadorUso",contadorUso);
            editor.commit();
        }
        else{
            contadorUso = sharedPreferences.getInt("contadorUso",0);
            contadorUso++;
            editor.putBoolean("isFirstRun", false);
            editor.putInt("contadorUso",contadorUso);
            editor.commit();
        }

        if (sharedPreferences.getBoolean("isFirstRun",true) && verificarConexao() == false){

            Toast.makeText(this,"Você precisa estar conectado para usar o aplicativo pela primeira vez",Toast.LENGTH_LONG).show();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                   finish();
                }
            },5000);

        }else{

//            editor.putBoolean("firstRun",false);
//            editor.commit();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent it = new Intent();
                    it.setClass(SplashActicity.this,MainActivity.class);
                    startActivity(it);
                    finish();
                }
            },4000);
        }
    }

    public boolean verificarConexao() {

        ConnectivityManager cm;
        NetworkInfo info;
        Context ctx = getApplicationContext();
        cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        info = cm.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }
}
