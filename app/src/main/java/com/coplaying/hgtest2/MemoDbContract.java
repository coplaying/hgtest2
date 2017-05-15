package com.coplaying.hgtest2;

import android.provider.BaseColumns;

public final class MemoDbContract {
    private MemoDbContract() {}

    public static class MemoDb implements BaseColumns {
        public static final String DB_NAME = "memo.db";
        public static final String TABLE_NAME = "memo";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String DATE = "date";
    }
}
