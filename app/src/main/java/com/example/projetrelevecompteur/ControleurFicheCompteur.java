package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ControleurFicheCompteur extends AppCompatActivity implements View.OnClickListener {

    CompteurSQLLite cbd ;
    Compteur c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controleur_fiche_compteur);

        cbd = new CompteurSQLLite(this);
        int pos = getIntent().getIntExtra("pos",-1);
        c = cbd.getListeCompteur().get(pos);

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

        EditText txtIndexNouveau=(EditText) findViewById(R.id.txtIndexNouveau);
        txtIndexNouveau.setText(c.indexNouveau + "");

        TextView txtNomReleveur=(TextView)findViewById(R.id.txtNomReleveur);
        txtNomReleveur.setText(c.nomReleveur);

        Button valider=findViewById(R.id.button1);
        Button annuler = findViewById(R.id.button2);
        valider.setOnClickListener(this);
        annuler.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:

                EditText txtIndexNouveau=(EditText) findViewById(R.id.txtIndexNouveau);
                c.indexNouveau= Integer.parseInt(txtIndexNouveau.getText().toString());
                cbd.updateIndexNouveau(c);
                finish();
                break;
        }


    }
}