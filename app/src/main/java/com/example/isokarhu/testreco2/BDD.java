package com.example.isokarhu.testreco2;

/**
 * Created by Axel on 20/12/2017.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
public class BDD extends SQLiteOpenHelper{
    private static final String TABLE_MOTS = "table_mots";
    private static final String TABLE_IMAGES = "table_images";
    private static final String COL_ID = "ID";
    private static final String COL_WORD = "WD";
    private static final String COL_FLAG = "FG";
    private static final String COL_IMAGE = "IM";


    private static final String CREATE_BDD_Wd = "CREATE TABLE " + TABLE_MOTS + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_WORD + " TEXT NOT NULL, "
            + COL_FLAG + " TEXT NOT NULL, " + COL_IMAGE + " TEXT NOT NULL);";

    private static final String CREATE_BDD_Im = "CREATE TABLE " + TABLE_IMAGES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_IMAGE + " TEXT NOT NULL);";

    public BDD(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD_Wd);
        db.execSQL(CREATE_BDD_Im);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_MOTS + ";");
        db.execSQL("DROP TABLE " + TABLE_IMAGES + ";");
        onCreate(db);

    }
}*/
public class BDD extends SQLiteOpenHelper {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotOrNot";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;
    private BDD ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;

    public BDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        this.ourContext = ourContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_HOTNESS + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }


    public void HotOrNot(Context c) {
        ourContext = c;
    }

    public BDD open() throws SQLException {
        ourHelper = new BDD(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }
}