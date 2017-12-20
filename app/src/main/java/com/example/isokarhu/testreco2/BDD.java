package com.example.isokarhu.testreco2;

/**
 * Created by Axel on 20/12/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BDD extends SQLiteOpenHelper{
    private static final String TABLE_MOTS = "table_mots";
    private static final String COL_ID = "ID";
    private static final String COL_WORD = "WD";
    private static final String COL_FLAG = "FG";
    private static final String COL_IMAGE = "IM";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MOTS + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_WORD + " TEXT NOT NULL, "
            + COL_FLAG + " TEXT NOT NULL, " + COL_IMAGE + " TEXT NOT NULL);";

    public BDD(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_MOTS + ";");
        onCreate(db);
    }
}
