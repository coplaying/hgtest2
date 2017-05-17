package com.coplaying.hgtest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class NoteDbHelper extends SQLiteOpenHelper {

    //database info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "note.db";

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NoteDbContract.NoteTable.SQL_CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NoteDbContract.NoteTable.SQL_DELETE_MEMO_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteDbContract.NoteTable.KEY_NOTETEXT, note.getNoteText());

        db.insert(NoteDbContract.NoteTable.TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Note> getNotes(){
        ArrayList<Note> noteList = new ArrayList<Note>();
        String SQL_SELECT_ALL = "SELECT * FROM " + NoteDbContract.NoteTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteText(cursor.getString(1));
                // Adding contact to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return noteList;
    }

}
