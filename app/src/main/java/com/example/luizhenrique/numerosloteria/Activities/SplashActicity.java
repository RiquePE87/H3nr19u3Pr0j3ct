package com.example.luizhenrique.numerosloteria.Activities;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.luizhenrique.numerosloteria.BuildConfig;
import com.example.luizhenrique.numerosloteria.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActicity extends AppCompatActivity {

    TextView tvVersao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testando);

        tvVersao = findViewById(R.id.tvVersao);
        tvVersao.setText("Versão "+BuildConfig.VERSION_NAME);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                Intent it = new Intent();
                it.setClass(SplashActicity.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        },5000);
    }
}
