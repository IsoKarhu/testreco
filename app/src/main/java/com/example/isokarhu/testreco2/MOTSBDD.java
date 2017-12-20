package com.example.isokarhu.testreco2;

/**
 * Created by Axel on 20/12/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MOTSBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

    private static final String TABLE_MOTS = "table_mots";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_WORD = "WD";
    private static final int NUM_COL_WORD = 1;
    private static final String COL_FLAG = "FG";
    private static final int NUM_COL_FLAG = 2;
    private static final String COL_IMAGE = "IM";
    private static final int NUM_COL_IMAGE= 3;

    private SQLiteDatabase bdd;

    private BDD maBaseSQLite;

    public MOTSBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new BDD(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertMots(Mots mots){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        // Revoir struct mots
        //values.put(COL_ID, mots.getId());
        values.put(COL_WORD, mots.getWord());
        values.put(COL_FLAG, mots.getFlag());
        values.put(COL_IMAGE, mots.getImage());
        //A revoir
        return bdd.insert(TABLE_MOTS, null, values);
    }

    public int updateMots(int id, Mots mots){
        // Revoir struct mots
        ContentValues values = new ContentValues();
        //values.put(COL_ID, mots.getId());
        values.put(COL_WORD, mots.getWord());
        values.put(COL_FLAG, mots.getFlag());
        values.put(COL_IMAGE, mots.getImage());
        //A revoir
        return bdd.update(TABLE_MOTS, values, COL_ID + " = " +id, null);
    }

    public int removeLivreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        //A revoir
        return bdd.delete(TABLE_MOTS, COL_ID + " = " +id, null);
    }

    public Mots getLivreWithTitre(String mots){
        Cursor c = bdd.query(TABLE_MOTS, new String[] {COL_ID, COL_WORD, COL_FLAG, COL_IMAGE}, COL_WORD + " LIKE \"" + mots +"\"", null, null, null, null);
        return cursorToLivre(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Mots cursorToLivre(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Mots mots = new Mots();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        mots.setId(c.getInt(NUM_COL_ID));
        mots.setWord(c.getString(NUM_COL_WORD));
        mots.setFlag(c.getString(NUM_COL_FLAG));
        mots.setImage(c.getString(NUM_COL_IMAGE));
        //On ferme le cursor
        c.close();

        //On retourne le mot
        return mots;
    }
}
