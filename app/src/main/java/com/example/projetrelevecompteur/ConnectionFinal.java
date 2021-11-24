package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import plum.widget.ComboDialog;

public class ConnectionFinal extends AppCompatActivity implements ComboDialog.OnClickComboDialogListener{

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


        ComboDialog comboCouleur = new ComboDialog( "Choisir un identifiant",
                itemsId,
                valuesMotDePasse,
                myTextViewItem,
                this );

        //Mise en place d'un écouteur sur ComboDialog
        comboCouleur.setOnClickComboDialogListener(this);

    }






    // Le choix dans ComboDialog déclenche un clic
    public void onClickComboDialog( ComboDialog comboDialog )
    {
        String value = (String) comboDialog.value( comboDialog.getIndexSelected());
        String item = (String) comboDialog.item( comboDialog.getIndexSelected());

        Toast toast = Toast.makeText(getApplicationContext(),
                value + ":" + item,
                Toast.LENGTH_LONG);
        toast.show();
    }
}