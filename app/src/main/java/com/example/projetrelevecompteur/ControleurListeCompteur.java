package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ControleurListeCompteur extends AppCompatActivity {
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

    }
}