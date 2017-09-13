package com.example.david.whatever.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David on 9/3/2017.
 */

public class MsgDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sheltesr.db";

    private static final int DATABASE_VERSION = 1;

    public MsgDbHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + MsgContract.MsgEntry.TABLE_NAME
                + " ("
                + MsgContract.MsgEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MsgContract.MsgEntry.COLUMN_CONTACT_NUMBER + " TEXT NOT NULL); ";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
