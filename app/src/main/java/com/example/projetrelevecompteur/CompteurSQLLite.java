package com.example.projetrelevecompteur;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompteurSQLLite extends SQLiteOpenHelper{
    //--- Base de données

    private static String DATABASE_NAME = "compteur.db";
    private static int DATABASE_VERSION = 6;

    //--- Table compteur

    private static String TABLE_COMPTEUR = "compteur";

    private static String COLUMN_ID = "id";
    private static String COLUMN_NOM = "nom";
    private static String COLUMN_RUE = "rue";
    private static String COLUMN_CODE_POSTAL = "codePostal";
    private static String COLUMN_VILLE = "ville";
    private static String COLUMN_INDEX_ANCIEN = "indexAncien";
    private static String COLUMN_INDEX_NOUVEAU = "indexNouveau";

    private static String COLUMNS_DEFINITION =
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOM + " TEXT,"
                    + COLUMN_RUE + " TEXT,"
                    + COLUMN_CODE_POSTAL + " TEXT,"
                    + COLUMN_VILLE + " TEXT,"
                    + COLUMN_INDEX_ANCIEN + " INTEGER,"
                    + COLUMN_INDEX_NOUVEAU + " INTEGER";


    /*
     * constructeur : création ou déclaration de la base de données
     *
     */
    public CompteurSQLLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /*
     * Appelé lorsque la base est crée pour la première fois
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        //--- create table compteur ---

        String sqlcreate = "create table " + TABLE_COMPTEUR
                + " (" + COLUMNS_DEFINITION + ");";

        db.execSQL (sqlcreate);

        //--- jeu d'essai ---

        Compteur t[] = getJeuDessai();

        for (Compteur co : t){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOM, co.nom);
            values.put(COLUMN_RUE, co.rue);
            values.put(COLUMN_VILLE, co.ville);
            values.put(COLUMN_CODE_POSTAL, co.codePostal);
            values.put(COLUMN_INDEX_ANCIEN, co.indexAncien);
            values.put(COLUMN_INDEX_NOUVEAU, co.indexNouveau);

            long insertId = db.insert(TABLE_COMPTEUR, null,values);
        }
    }

    public void updateIndexNouveau(Compteur c){
        String where = "id = " + c.id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INDEX_NOUVEAU, c.indexNouveau);

        db.update(TABLE_COMPTEUR,
                values, where, null);
    }

    /*
     * Appelé lorsque la base a besoin d'être modifiée
     * Il suffit de modifier DATABASE_VERSION !
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTEUR + ";");
        onCreate(db);
    }

    /*
     * retourne un ArraList contenant les données de la table compteur
     */
    public ArrayList <Compteur> getListeCompteur(){
        ArrayList<Compteur> ar = new ArrayList<Compteur>();

        SQLiteDatabase db = this.getWritableDatabase();

        //ar.add(getJeuDessai()[0]);
        String columns[]={COLUMN_ID, COLUMN_NOM, COLUMN_VILLE, COLUMN_RUE,
                COLUMN_CODE_POSTAL, COLUMN_INDEX_ANCIEN, COLUMN_INDEX_NOUVEAU
        };
        Cursor cursor = db.query(TABLE_COMPTEUR, columns ,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Compteur co = new Compteur();

            co.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            co.nom = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
            co.rue = cursor.getString(cursor.getColumnIndex(COLUMN_RUE));
            co.ville = cursor.getString(cursor.getColumnIndex(COLUMN_VILLE));
            co.codePostal = cursor.getString(cursor.getColumnIndex(COLUMN_CODE_POSTAL));
            co.indexAncien = cursor.getInt(cursor.getColumnIndex(COLUMN_INDEX_ANCIEN));
            co.indexNouveau = cursor.getInt(cursor.getColumnIndex(COLUMN_INDEX_NOUVEAU));
            co.nomReleveur="";

            ar.add(co);

            cursor.moveToNext();
        }

        return ar;
    }

    @SuppressLint("Range")
    public int getNewIndexCompteurById (int id){
        String w= "id=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        String columns[]= {COLUMN_INDEX_NOUVEAU};

        Cursor cursor = db.query(TABLE_COMPTEUR, columns ,w,null,null,null,null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(COLUMN_INDEX_NOUVEAU));
    }

    /*
     * Génération du jeu d'essai
     */
    private Compteur[] getJeuDessai(){
        String[] tVille = {"Limoges", "Couzeix", "Panazol"};

        String[] tCodePostal = {"87000", "87200", "87300"};

        String[] tNom = {"Pasqualini", "Bogusz", "Techer", "Bourgeois", "Tournie"};

        String[] tRue = {"Rue de la Palisse",
                "Rue des Petits Pois",
                "Rue des Milles Etangs",
                "Rue François Perrin",
                "Rue Turgot"};

        int ct = 20;
        Compteur t[] = new Compteur[ct];

        for(int i = 0; i < ct ; i++){
            int iville = (int)(Math.random() * tVille.length);
            int inom = (int)(Math.random() * tNom.length);
            int irue = (int) (Math.random() * tRue.length);

            Compteur co = new Compteur();

            co.nom = tNom[inom];
            co.ville = tVille[iville];
            co.codePostal = tCodePostal[iville];
            co.indexAncien = (int)( Math.random() * 20000);
            co.indexNouveau = 0;
            co.rue = tRue[irue];

            t[i] = co;
        }

        return t;
    }

    /*
     * retourne un ArrayList contenant les noms et mot de passe des visiteurs
     */
    public ArrayList <Releveur> getListeReleveur(){

        ArrayList<Releveur> liste = new ArrayList();

        String[] tNom = {"Claude", "Thierry", "Charles", "Agnès", "Alain"};

        for ( String nom : tNom ){
            Releveur r = new Releveur();
            r.nomReleveur = nom;
            r.motDePasse = nom;

            liste.add ( r );
        }

        return liste;

    }

}
