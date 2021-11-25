package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ControleurFicheCompteur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controleur_fiche_compteur);

        CompteurSQLLite cbd = new CompteurSQLLite(this);
        Compteur c = cbd.getListeCompteur().get(0);


    }
}