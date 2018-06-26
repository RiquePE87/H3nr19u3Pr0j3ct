package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();
    }
}
