package com.example.projetrelevecompteur;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CompteurArrayAdapter extends ArrayAdapter<Compteur> {
    public CompteurArrayAdapter(Context context, ArrayList<Compteur> compteurs) {
        super(context, 0, compteurs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Compteur compteur = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_compteur,
                    parent,
                    false);
        }


            TextView txtNom = (TextView) convertView.findViewById(R.id.txtNom);
            TextView txtRue = (TextView) convertView.findViewById(R.id.txtRue);
            TextView txtCodePostal = (TextView) convertView.findViewById(R.id.txtCodePostal);
            TextView txtVille = (TextView) convertView.findViewById(R.id.txtVille);
            TextView txtIndexAncien = (TextView) convertView.findViewById(R.id.txtIndexAncien);
            TextView txtIndexNouveau = (TextView) convertView.findViewById(R.id.txtIndexNouveau);
            TextView txtNomReleveur = (TextView) convertView.findViewById(R.id.txtNomReleveur);

            txtNom.setText(compteur.nom);
            txtRue.setText(compteur.rue);
            txtCodePostal.setText(compteur.codePostal);
            txtVille.setText(compteur.ville);
            txtIndexAncien.setText(compteur.indexAncien + "");
            txtIndexNouveau.setText(compteur.indexNouveau + "");
            txtNomReleveur.setText(compteur.nomReleveur);

        if(compteur.indexNouveau==0) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.waited);
        }else {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.checked);
        }

        return convertView;
    }
}
