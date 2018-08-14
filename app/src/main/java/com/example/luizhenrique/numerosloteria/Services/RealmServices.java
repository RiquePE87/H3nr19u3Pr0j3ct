package com.example.luizhenrique.numerosloteria.Services;

import android.content.Context;
import android.widget.Toast;
import com.example.luizhenrique.numerosloteria.Activities.AdicionarJogo;
import com.example.luizhenrique.numerosloteria.Model.Jogo;
import com.example.luizhenrique.numerosloteria.Model.Resultado;
import com.example.luizhenrique.numerosloteria.View.AdicionarJogoView;
import java.util.List;
import java.util.Random;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmServices {

    private Context context;
    //private Realm realm;
    String cas;

    AdicionarJogoView adicionarJogoView;

   public RealmServices(Context ctx){

       this.context = ctx;
        adicionarJogoView = new AdicionarJogo();
   }

   public int gerarIDJogo(){

       Random rdm = new Random();
       int id;
       boolean isValido = false;

       id = rdm.nextInt(100);

       while (isValido != true){

           isValido = verificarIDJogo(id);
       }

       return id;
   }

    public void salvarJogo(Jogo j){

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Jogo jogo = realm.createObject(Jogo.class);
        jogo.id = gerarIDJogo();
        jogo.NumeroDezenas = j.NumeroDezenas;
        jogo.numeros = j.numeros;
        jogo.sorteio = j.sorteio;
        jogo.timeDoCoracao = j.timeDoCoracao;
        jogo.tipoJogo = j.tipoJogo;
        jogo.mesDeSorte = j.mesDeSorte;
        jogo.filename = j.filename;
        realm.commitTransaction();

        realm.close();
    }

    public void atualizarJogo(int id, int sorteio) {

       Jogo jogo = getJogo(id);

        try {

            Realm.init(context);

            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            jogo.sorteio = sorteio;
            realm.insertOrUpdate(jogo);

            realm.commitTransaction();

            realm.close();

        } catch (Exception ex) {

        Toast.makeText(context,ex.getMessage(),Toast.LENGTH_LONG).show();

        }
    }

    public Jogo getJogo(int id){

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        Jogo jogoSalvo = realm.where(Jogo.class).equalTo("id", id).findFirst();

        realm.close();

        return jogoSalvo;

    }

    public boolean verificarIDJogo(int id){

       boolean isValido;
        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();


        isValido = realm.where(Jogo.class).equalTo("id", id).findFirst() == null;

            realm.close();


        return isValido;
    }

    public List<Jogo> carregarJogos() {

       List<Jogo> jogos = null;

        try {

            Realm.init(context);

            Realm realm = Realm.getDefaultInstance();

            jogos = realm.where(Jogo.class).findAll();

        } catch (Exception ex) {

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG);
        }

        return jogos;
    }

    public void ExcluirJogo(final Jogo jogo){

        try {
            Realm.init(context);

            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    RealmResults<Jogo> result = realm.where(Jogo.class).equalTo("id",jogo.id).findAll();
                    result.deleteAllFromRealm();
                }
            });

            Toast.makeText(context,"Aposta excluída com sucesso",Toast.LENGTH_LONG).show();

        }catch (Exception ex){

        }
    }
}
