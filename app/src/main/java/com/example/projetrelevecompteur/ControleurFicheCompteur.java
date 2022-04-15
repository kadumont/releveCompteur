package com.example.projetrelevecompteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import plum.widget.MessageDialog;

public class ControleurFicheCompteur extends AppCompatActivity implements View.OnClickListener,
        MessageDialog.OnClickMessageDialogListener {

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

        Button valider = findViewById(R.id.button1);
        Button annuler = findViewById(R.id.button2);
        valider.setOnClickListener(this);
        annuler.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:

                EditText txtIndexNouveau=(EditText) findViewById(R.id.txtIndexNouveau);

                // nouvel index reste vide
                if (txtIndexNouveau.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Nouvel index vide! ",
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                c.indexNouveau= Integer.parseInt(txtIndexNouveau.getText().toString());

                //nouvel index est inférieur à l'ancien index
                if (c.indexNouveau <= c.indexAncien){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Nouvel index inférieur ou égale  à l'ancien! ",
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }


                if ((c.indexNouveau - c.indexAncien) > 800 ){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Index superieur à 800! ",
                            Toast.LENGTH_LONG);
                    toast.show();}

                    cbd.updateIndexNouveau(c);
                    finish();

                break;

            case R.id.button2:

                MessageDialog.show(this,
                        "Souhaitez-vous annuler?",
                        "OUI","NON", this);
                break;
        }
    }




    @Override
    public void onClickMessageDialog(MessageDialog messageDialog, char c) {
        switch (c) {
            case 'G':
                finish();
                break;
            case 'D':
                break;
        }
    }
}