package com.coplaying.hgtest2;

import android.provider.BaseColumns;

public final class NoteDbContract {

    private NoteDbContract() {}

    public static class NoteTable implements BaseColumns {

        public static final String TABLE_NAME = "notes";
        public static final String KEY_NOTETEXT = "note_text";

        public static final String SQL_CREATE_NOTE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        KEY_NOTETEXT + " TEXT" + ")";
        public static final String SQL_DELETE_MEMO_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
