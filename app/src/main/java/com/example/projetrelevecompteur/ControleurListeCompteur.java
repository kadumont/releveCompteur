package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ControleurListeCompteur extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private ListView lv;
    Compteur compteur=null;
    CompteurArrayAdapter arrayAdapterCompteur;
    CompteurSQLLite s;
    Spinner cityArea;
    Activity my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        my=this;
        setContentView(R.layout.layout_liste_compteur);

        cityArea = (Spinner) findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.list);
        s = new CompteurSQLLite(this);
        ArrayList<Compteur> lesCompteurs = s.getListeCompteur();

        arrayAdapterCompteur = new CompteurArrayAdapter(this, lesCompteurs);


        cityArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        arrayAdapterCompteur = new CompteurArrayAdapter(my,
                                s.getListeCompteur());
                        lv.setAdapter(arrayAdapterCompteur );
                        arrayAdapterCompteur.notifyDataSetChanged();
                        break;


                    //afficher client à faire
                    case 1:
                        arrayAdapterCompteur = new CompteurArrayAdapter(my,
                                s.getListeCompteurNonFait());
                        lv.setAdapter(arrayAdapterCompteur);
                        arrayAdapterCompteur.notifyDataSetChanged();
                        break;

                    //Afficher client fait
                    case 2:
                        arrayAdapterCompteur = new CompteurArrayAdapter(my,
                                s.getListeCompteurFait());
                        lv.setAdapter(arrayAdapterCompteur);
                        arrayAdapterCompteur.notifyDataSetChanged();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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

    public void envoyerConnexion (View view) {
        finish();
    }
}