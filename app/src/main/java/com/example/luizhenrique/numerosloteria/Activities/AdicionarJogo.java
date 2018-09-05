package com.example.luizhenrique.numerosloteria.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Presenter.AdicionarJogoPresenter;
import com.example.luizhenrique.numerosloteria.Presenter.AdicionarJogoPresenterImpl;
import com.example.luizhenrique.numerosloteria.R;
import com.example.luizhenrique.numerosloteria.Services.GeradorDeNumeros;
import com.example.luizhenrique.numerosloteria.Services.JogoManager;
import com.example.luizhenrique.numerosloteria.Services.RealmServices;
import com.example.luizhenrique.numerosloteria.Services.ResultadoService;
import com.example.luizhenrique.numerosloteria.View.AdicionarJogoView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AdicionarJogo extends AppCompatActivity implements AdicionarJogoView {

    Spinner spinner_tipoJogo;
    Spinner spinner_NumeroDezenas;
    TextView tvNumerosGerados;
    TextView tvMeses;
    EditText etSorteio;
    Button btnGerarNumeros;
    Button btnEscolherNumeros;
    Button btnMaisSorteados;
    Button btnProximoConcurso;
    Button btnCapturarNumeros;
    Toolbar toolbar;
    TextView tvTimesdoCoracao;
    Spinner spinnerTimes;
    Spinner spinnerMeses;
    ProgressBar progressDialog;
    ArrayAdapter<String> numeroDesenasJogo;
    Context ctx;
    public NetworkInfo info;
    public Jogo jogo;
    AdView adView;


    private InterstitialAd mInterstitialAd;

    private RealmServices realmServices;

    public String tipoJogo;

    public String numeros;

    public String timeDoCoracao;

    public String mesDeSorte;

    public int rangeJogo;

    public int numeroDezenas;

    int minAposta;

    Intent it;

    AdicionarJogoPresenter adicionarJogoPresenter;

    JogoManager jogoManager;

    ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jogoManager = new JogoManager();

        realmServices = new RealmServices(getApplicationContext());

        setContentView(R.layout.activity_adicionar_jogo);

        adicionarJogoPresenter = new AdicionarJogoPresenterImpl(this);

        it = getIntent();

        ctx = getBaseContext();

        cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        info = cm.getActiveNetworkInfo();

        spinner_tipoJogo = findViewById(R.id.spinnerJogo);
        spinner_NumeroDezenas = findViewById(R.id.spinnerDezenas);
        tvNumerosGerados = findViewById(R.id.textViewNumerosGerados);
        etSorteio = findViewById(R.id.editTextSorteio);
        btnGerarNumeros = findViewById(R.id.buttonGerar);
        toolbar = findViewById(R.id.toolbar2);
        btnEscolherNumeros = findViewById(R.id.buttonEscolher);
        btnMaisSorteados = findViewById(R.id.btnMaisSorteados);
        tvTimesdoCoracao = findViewById(R.id.txtTimesdoCoracao);
        tvMeses = findViewById(R.id.txtMeses);
        spinnerTimes = findViewById(R.id.spinnerTimes);
        spinnerMeses = findViewById(R.id.spinnerMeses);
        progressDialog = findViewById(R.id.pbadicionarJogo);
        btnProximoConcurso = findViewById(R.id.btnProximoConcurso);
        btnCapturarNumeros = findViewById(R.id.buttonCapturarNumeros);
        adView = findViewById(R.id.adViewAdicionar);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1281837718502232/9576019966");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        setSupportActionBar(toolbar);

        numeroDesenasJogo = new ArrayAdapter<>(AdicionarJogo.this,android.R.layout.simple_spinner_item);
        numeroDesenasJogo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        setView();

        spinner_tipoJogo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tipoJogo = spinner_tipoJogo.getSelectedItem().toString();
                gerarTipoJogoView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_NumeroDezenas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                numeroDezenas = Integer.parseInt(spinner_NumeroDezenas.getSelectedItem().toString());

                if (it.getExtras() == null){
                    tvNumerosGerados.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                timeDoCoracao = spinnerTimes.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mesDeSorte = spinnerMeses.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGerarNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdicionarJogo.this, NumerosFavoritos.class);
                it.putExtra("jogo", tipoJogo);
                it.putExtra("dezenas", numeroDezenas);
                it.putExtra("numeroBolas", rangeJogo);
                startActivityForResult(it, 3);

            }
        });

        btnEscolherNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AdicionarJogo.this, SelecaoNumeros.class);
                it.putExtra("jogo", tipoJogo);
                it.putExtra("dezenas", numeroDezenas);
                it.putExtra("numeroBolas", rangeJogo);
                startActivityForResult(it, 1);
            }
        });

        btnMaisSorteados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (info != null && info.isConnected())
                tvNumerosGerados.setText(GeradorDeNumeros.ParseToString(adicionarJogoPresenter.numerosMaisSorteados(tipoJogo,numeroDezenas,minAposta)));
                else
                    Toast.makeText(ctx,"Para usar essa função, você precisa estar conectado a internet",Toast.LENGTH_LONG).show();
            }
        });

        btnProximoConcurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (info != null && info.isConnected())
                etSorteio.setText(String.valueOf(adicionarJogoPresenter.jogarProximoConcurso(tipoJogo)));
                else
                    Toast.makeText(ctx,"Para usar essa função, você precisa estar conectado a internet",Toast.LENGTH_LONG).show();

            }
        });

//        btnCapturarNumeros.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent it = new Intent(AdicionarJogo.this,CameraOCRActivity.class);
//                startActivityForResult(it,2);
//            }
//        });

    }

    public void gerarTipoJogoView(){

        numeroDesenasJogo.clear();
        etSorteio.setText("");

        if (!isAtualizacao(it)){
            tvNumerosGerados.setText("");
        }

        spinnerTimes.setVisibility(View.GONE);
        tvTimesdoCoracao.setVisibility(View.GONE);
        tvMeses.setVisibility(View.GONE);
        spinnerMeses.setVisibility(View.GONE);

        String[] tipoJogoJogadas = jogoManager.getTipoJogoNumeros(tipoJogo.toLowerCase());
        int rangeTipoJogo = jogoManager.getRangeJogo(tipoJogo.toLowerCase());

        minAposta = Integer.parseInt(tipoJogoJogadas[0]);
        numeroDesenasJogo.addAll(tipoJogoJogadas);
        spinner_NumeroDezenas.setAdapter(numeroDesenasJogo);
        rangeJogo = rangeTipoJogo;

        if (tipoJogo.equals("TimeMania")){
            tvTimesdoCoracao.setVisibility(View.VISIBLE);
            spinnerTimes.setVisibility(View.VISIBLE);
        }else if (tipoJogo.equals("Dia-de-Sorte")){

            tvMeses.setVisibility(View.VISIBLE);
            spinnerMeses.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setNumerosError() {

        exibirToast("Entre com os números da aposta!");
    }

    @Override
    public void setSorteioError() {

        etSorteio.setError("Entre com o sorteio da aposta");

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater  menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_adicionar_jogo,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.actionSalvar:

                if (it.getExtras() != null){
                    realmServices.atualizarJogo(it.getIntExtra("id",0),Integer.valueOf(etSorteio.getText().toString()), ResultadoService.getFilename(tipoJogo,etSorteio.getText().toString()));
                    finish();

                }else{

                    if (adicionarJogoPresenter.validarJogo(tvNumerosGerados.getText().toString(), etSorteio.getText().toString())){
                        realmServices.salvarJogo(pegarDadosJogo());
                        exibirToast("Aposta salva com sucesso!");
                        exibirPublicidade();

                        finish();
                        return true;
                    }
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1){

            numeros = data.getStringExtra("numerosSelec");
            tvNumerosGerados.setText(numeros);

        }else if (resultCode == RESULT_OK && requestCode == 2){

            String numerosCapturados = data.getStringExtra("numerosCapturados");
            tvNumerosGerados.setText(numerosCapturados);
        }
        else if (requestCode == 3 && resultCode == RESULT_OK){

            String numerosFavoritos = data.getStringExtra("numerosFavoritos");
            tvNumerosGerados.setText(numerosFavoritos);
        }
    }


    @Override
    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getCount(); i++)
        {
            if(spin.getItemAtPosition(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }
    }

    @Override
    public void exibirToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void exibirPublicidade() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

    }

    @Override
    public boolean isAtualizacao(Intent it) {

        return it.getExtras() != null;
    }

    @Override
    public void setView() {

        if (isAtualizacao(it)){

            Jogo jogo = realmServices.getJogo(it.getIntExtra("id", 0));

            setTitle("Atualizar Aposta");
            setSpinText(spinner_NumeroDezenas, String.valueOf(jogo.NumeroDezenas));
            setSpinText(spinner_tipoJogo, jogo.tipoJogo);

            if (jogo.tipoJogo.equals("dia-de-sorte"))
            setSpinText(spinnerMeses,jogo.mesDeSorte);

            spinner_tipoJogo.setEnabled(false);
            spinner_NumeroDezenas.setEnabled(false);
            btnEscolherNumeros.setEnabled(false);
            btnGerarNumeros.setEnabled(false);
            btnMaisSorteados.setEnabled(false);
            tvNumerosGerados.setText(jogo.numeros);
            etSorteio.setText(String.valueOf(jogo.sorteio));

        }
        else{
            setTitle("Nova Aposta");
        }
    }

    @Override
    public Jogo pegarDadosJogo() {

        Jogo jogo = new Jogo();

        jogo.NumeroDezenas = numeroDezenas;
        jogo.numeros = tvNumerosGerados.getText().toString();
        jogo.sorteio = Integer.valueOf(etSorteio.getText().toString());
        jogo.tipoJogo = tipoJogo;
        jogo.timeDoCoracao = timeDoCoracao;
        jogo.mesDeSorte = mesDeSorte;
        jogo.filename = ResultadoService.getFilename(tipoJogo,etSorteio.getText().toString());
        new ResultadoService().salvarResultadoJogo(tipoJogo,etSorteio.getText().toString());

        return jogo;
    }

    @Override
    public void exibirProgress(boolean exibir) {
        progressDialog.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }
}
