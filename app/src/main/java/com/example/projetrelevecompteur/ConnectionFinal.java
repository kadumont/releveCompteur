package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import plum.widget.ComboDialog;

public abstract class ConnectionFinal extends AppCompatActivity implements ComboDialog.OnClickComboDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_final);
    }

    TextView myTextViewItem = (TextView)findViewById( R.id.idUtilisateur );

    ArrayList<Releveur> listeReleveur = new ArrayList();


    final CharSequence[] itemsId = {"Rouge","Vert","Bleu"}; //identifiant
    final CharSequence[] valuesMotDePasse = {"1","2","3"}; //mot de passe

    for(int i = 0, listeReleveurw ){

    }


    ComboDialog comboCouleur = new ComboDialog( "Choisir un identifiant",
            itemsId,
            itemsId,
            myTextViewItem,
            this );

    //Mise en place d'un écouteur sur ComboDialog
    comboCouleur.setOnClickComboDialogListener(this);



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