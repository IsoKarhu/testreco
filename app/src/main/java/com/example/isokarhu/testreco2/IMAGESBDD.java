package com.example.isokarhu.testreco2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Axel on 23/12/2017.
 */

public class IMAGESBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

    private static final String TABLE_IMAGES = "table_images";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_IMAGE = "IM";
    private static final int NUM_COL_IMAGE= 1;

    private SQLiteDatabase bdd;

    private BDD maBaseSQLite;

    public IMAGESBDD(Context context){
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

    public long insertImages(Images images){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        values.put(COL_IMAGE, images.getImage());
        //A revoir
        return bdd.insert(TABLE_IMAGES, null, values);
    }

    public int updateImages(int id, Images images){
        // Revoir struct mots
        ContentValues values = new ContentValues();
        values.put(COL_IMAGE, images.getImage());
        //A revoir
        return bdd.update(TABLE_IMAGES, values, COL_ID + " = " +id, null);
    }

    public int getlength () {
        Cursor lll = bdd.rawQuery("select COL_ID from " + TABLE_IMAGES, null);
        return lll.getCount();
    }

    public int removeImagesWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        //A revoir
        return bdd.delete(TABLE_IMAGES, COL_ID + " = " +id, null);
    }

    public Images getImagesWithName(String images){
        Cursor c = bdd.query(TABLE_IMAGES, new String[] {COL_ID, COL_IMAGE}, COL_IMAGE + " LIKE \"" + images +"\"", null, null, null, null);
        return cursorToImages(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Images cursorToImages(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Images images = new Images();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        images.setId(c.getInt(NUM_COL_ID));
        images.setImage(c.getString(NUM_COL_IMAGE));
        //On ferme le cursor
        c.close();

        //On retourne le mot
        return images;
    }
}
