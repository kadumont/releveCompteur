package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import plum.widget.ComboDialog;

public class ConnectionFinal extends AppCompatActivity implements  ComboDialog.OnClickComboDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_final);
    }

    TextView myTextViewItem = (TextView)findViewById( R.id.idUtilisateur );

    final CharSequence[] items = {"Rouge","Vert","Bleu"};
    final CharSequence[] values = {"1","2","3"};

    ComboDialog comboCouleur = new ComboDialog( "Choisir une couleur",
            items,
            values,
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