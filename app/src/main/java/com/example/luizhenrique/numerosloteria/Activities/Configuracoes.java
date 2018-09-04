package com.example.luizhenrique.numerosloteria.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.luizhenrique.numerosloteria.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Configuracoes extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    CheckBox chkMega;
    CheckBox chkLotoFacil;
    CheckBox chkLotoMania;
    CheckBox chkQuina;
    CheckBox chkDupla;
    CheckBox chkDia;
    CheckBox chkTime;
    SharedPreferences.Editor editor;
    Set<String> jogos;
    List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        chkMega = findViewById(R.id.chkMega);
        chkLotoFacil = findViewById(R.id.chkLotofacil);
        chkLotoMania = findViewById(R.id.chkLotomania);
        chkDupla = findViewById(R.id.chkDupla);
        chkQuina = findViewById(R.id.chkQuina);
        chkTime = findViewById(R.id.chkTime);
        chkDia = findViewById(R.id.chkdia);

        checkBoxList.add(chkMega);
        checkBoxList.add(chkLotoFacil);
        checkBoxList.add(chkLotoMania);
        checkBoxList.add(chkDia);
        checkBoxList.add(chkQuina);
        checkBoxList.add(chkTime);
        checkBoxList.add(chkDupla);

        sharedPreferences = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        jogos = sharedPreferences.getStringSet("jogos",null);

        carregarCheckBoxes();
    }

    public void carregarCheckBoxes(){

        for (String s: jogos){
            for (CheckBox e: checkBoxList){
                if (s.equals(e.getText())){
                    e.setChecked(true);
                }
            }
        }
    }

    public void onCheckBoxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        String text = ((CheckBox) view).getText().toString();

        if (checked){

            jogos.add(((CheckBox) view).getText().toString());
            editor.putStringSet("jogos",jogos);
            editor.commit();
        }else{
            jogos.remove(text);
            editor.putStringSet("jogos",jogos);
            editor.commit();
        }
    }
}
