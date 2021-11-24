package com.example.projetrelevecompteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_liste_compteur,
                    parent,
                    false);
        }

        // Lookup view for data population
        TextView txtNom = (TextView) convertView.findViewById(R.id.txtNom);


        // Populate the data into the template view using the data object
        txtNom.setText(compteur.nom);

        // Return the completed view to render on screen
        return convertView;
    }
}
