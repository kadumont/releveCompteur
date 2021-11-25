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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_compteur);


        CompteurSQLLite s = new CompteurSQLLite(this);
        ArrayList<Compteur> lesCompteurs = s.getListeCompteur();

        CompteurArrayAdapter arrayAdapterCompteur =
                new CompteurArrayAdapter(this, lesCompteurs);

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(arrayAdapterCompteur );

        /** écouteur */
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> ad, View v, int pos, long id) {
        // When clicked, show a toast with the TextView text
        Intent intent = new Intent(this,ControleurFicheCompteur.class);
        startActivity(intent);
    }
}