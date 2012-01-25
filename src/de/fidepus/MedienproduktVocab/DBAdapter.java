package de.fidepus.MedienproduktVocab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	public static final String KEY_ROWDE = "DE";
	public static final String KEY_ROWEN = "_EN";
	public static final String KEY_ROWGELERNT = "gelernt";
	
    private static final String DATABASE_NAME = "vokabeln";
    private static final String DATABASE_TABLE = "englisch";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_CREATE =
        "create table titles (_id integer primary key autoincrement, "
        + "DE text not null, EN text not null, " 
        + "gelernt text not null);";
        
    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
}