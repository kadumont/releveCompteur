package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ControleurFicheCompteur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controleur_fiche_compteur);

        CompteurSQLLite cbd = new CompteurSQLLite(this);
        Compteur c = cbd.getListeCompteur().get(0);

        TextView txtNom=(TextView)findViewById(R.id.txtNom);
        txtNom.setText(c.nom);

        TextView txtRue=(TextView)findViewById(R.id.txtRue);
        txtRue.setText(c.rue);

        TextView txtCodePostal=(TextView)findViewById(R.id.txtCodePostal);
        txtCodePostal.setText(c.codePostal);

        TextView txtVille=(TextView)findViewById(R.id.txtVille);
        txtVille.setText(c.ville);

        TextView txtAncienIndex=(TextView)findViewById(R.id.txtIndexAncien);
        txtAncienIndex.setText(c.indexAncien + "");

        TextView txtIndexNouveau=(TextView)findViewById(R.id.txtIndexNouveau);
        txtIndexNouveau.setText(c.indexNouveau + "");

        TextView txtNomReleveur=(TextView)findViewById(R.id.txtNomReleveur);
        txtNomReleveur.setText(c.nomReleveur);

    }
}