package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Btn Connexion
    public void envoyerConnexion (View view) {
        Intent intent = new Intent(this,ConnectionFinal.class);

        startActivity(intent);
    }

    //Btn ListeCompteur
    public void envoyerListeCompteur (View view) {
        Intent intent = new Intent(this,ControleurListeCompteur.class);

        startActivity(intent);
    }

    //Btn FicheCompteur
    public void envoyerFicheCompteur (View view) {
        Intent intent = new Intent(this,ControleurFicheCompteur.class);

        startActivity(intent);
    }
}