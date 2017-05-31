package com.android1.gabriel.blocdenota;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel on 27/05/2017.
 */

public class AdaptadorBD extends SQLiteOpenHelper {
    public static final String TABLE_ID = "_idNote";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    private static final String DATABASE = "Note";
    private static final String TABLE = "notes";
    public AdaptadorBD(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE "+ TABLE +" ("+
          TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
     TITLE+ " TEXT,"+CONTENT + "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }
    public void addNota(String title, String content){
        ContentValues valores = new ContentValues();
        valores.put(TITLE,title);
        valores.put(CONTENT,content);
        this.getWritableDatabase().insert(TABLE,null,valores);

    }

    //Metodo que devuelve nota
    public Cursor getNote(String condition) {
        String columnas [] = {TABLE_ID,TITLE,CONTENT};
        String [] args = new String[] {condition};
        Cursor c = this.getReadableDatabase().query(TABLE, columnas, TITLE+"=?", args, null, null, null);
        return c;
    }
}
