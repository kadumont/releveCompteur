package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ControleurListeCompteur extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    Compteur compteur=null;
    CompteurArrayAdapter arrayAdapterCompteur;
    CompteurSQLLite s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_compteur);


        s = new CompteurSQLLite(this);
        ArrayList<Compteur> lesCompteurs = s.getListeCompteur();

        arrayAdapterCompteur =
                new CompteurArrayAdapter(this, lesCompteurs);

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(arrayAdapterCompteur );

        /** écouteur */
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> ad, View v, int pos, long id) {
        compteur=(Compteur)ad.getItemAtPosition(pos);
        Intent intent = new Intent(this,ControleurFicheCompteur.class);
        intent.putExtra("pos", pos);

        startActivity(intent);
        onRestart();
    }

    public void onRestart() {
        super.onRestart();
        compteur.indexNouveau  = s.getNewIndexCompteurById(compteur.id);

        arrayAdapterCompteur.notifyDataSetChanged();
    }



}