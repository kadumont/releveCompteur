package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import plum.widget.ComboDialog;

public class ConnectionFinal extends AppCompatActivity
        implements ComboDialog.OnClickComboDialogListener, View.OnClickListener {

    String valueMotDePasse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_final);

        CompteurSQLLite sqlLite = new CompteurSQLLite(this);
        TextView myTextViewItem = (TextView)findViewById( R.id.idUtilisateur );


        ArrayList<Releveur> listeReleveur = sqlLite.getListeReleveur();

        final CharSequence[] itemsId  = new CharSequence[listeReleveur.size()];
        final CharSequence[] valuesMotDePasse = new CharSequence[listeReleveur.size()];

        for(int i = 0; i < listeReleveur.size() ; i++ ){
            itemsId[i] = listeReleveur.get(i).nomReleveur;
            valuesMotDePasse[i] = listeReleveur.get(i).motDePasse;
        }


        ComboDialog comboIdentifiant = new ComboDialog( "Choisir un identifiant",
                itemsId,
                valuesMotDePasse,
                myTextViewItem,
                this );

        //Mise en place d'un écouteur sur ComboDialog
        comboIdentifiant.setOnClickComboDialogListener(this);

        Button monBouton=(Button) findViewById(R.id.login);
        monBouton.setOnClickListener(this);

    }


    // Le choix dans ComboDialog déclenche un clic
    public void onClickComboDialog( ComboDialog comboDialog ) {
        valueMotDePasse = (String) comboDialog.value( comboDialog.getIndexSelected());
    }


    @Override
    public void onClick(View v) {
        //TextView myTextViewItem = (TextView)findViewById( R.id.idUtilisateur );
        EditText myEditText = (EditText)findViewById(R.id.mdp);

        if (myEditText.getText().toString().equals(valueMotDePasse)){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Connexion réussie ! ",
                    Toast.LENGTH_LONG);
            toast.show();

            //StartActivity sur Liste
            Intent intent = new Intent(this,ControleurListeCompteur.class);
            startActivity(intent);

        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Information incorrect ! ",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }
}