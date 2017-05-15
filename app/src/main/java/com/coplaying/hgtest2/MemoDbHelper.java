package com.coplaying.hgtest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MemoDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_MEMO_TABLE =
            "CREATE TABLE " + MemoDbContract.MemoDb.TABLE_NAME + " (" +
            MemoDbContract.MemoDb._ID + " INTEGER PRIMARY KEY," +
            MemoDbContract.MemoDb.TITLE + " TEXT," +
            MemoDbContract.MemoDb.CONTENT + " TEXT,)";
    private static final String SQL_DELETE_MEMO_TABLE =
            "DROP TABLE IF EXIST " + MemoDbContract.MemoDb.TABLE_NAME;

    public MemoDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MEMO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
