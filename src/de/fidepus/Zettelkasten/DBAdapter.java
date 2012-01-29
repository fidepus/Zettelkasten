package de.fidepus.Zettelkasten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_DE = "de";
    public static final String KEY_EN = "en";
    public static final String KEY_GELERNT = "gelernt";    
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "vocab";
    private static final String DATABASE_TABLE = "english";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table titles (_id integer primary key autoincrement, "
        + "de text not null, en text not null, " 
        + "gelernt int not null);";
        
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
    
    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertTitle(String de, String en, String gelernt) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DE, de);
        initialValues.put(KEY_EN, en);
        initialValues.put(KEY_GELERNT, gelernt);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular title---
    public boolean deleteTitle(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + 
        		"=" + rowId, null) > 0;
    }

    //---retrieves all the titles---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID, 
        		KEY_DE,
        		KEY_EN,
                KEY_GELERNT}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves a particular title---
    public Cursor getTitle(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_DE, 
                		KEY_EN,
                		KEY_GELERNT
                		}, 
                		KEY_ROWID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a title---
    public boolean updateTitle(long rowId, String de, 
    String en, String gelernt) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_DE, de);
        args.put(KEY_EN, en);
        args.put(KEY_GELERNT, gelernt);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
}