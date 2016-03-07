package com.github.vavooon.aresdroid.entity;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.github.vavooon.aresdroid.AresLog;

public class AresValidLanguage {
    private static final String LOG_TAG = AresValidLanguage.class.getSimpleName();

    public int id;
    public String code;
    public String label;

    public AresValidLanguage(int id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    public static final class db implements BaseColumns {
        public static final String TABLE_NAME = "valid_language";

        public static final String COLUMN_ID = "valid_language_id";
        public static final String COLUMN_CODE = "valid_language_code";
        public static final String COLUMN_LABEL = "valid_language_label";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_CODE = COLUMN_INDEX_ID + 1;
        public static final int COLUMN_INDEX_LABEL = COLUMN_INDEX_CODE + 1;
    }

    public static void createValidLanguageTable(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_VALID_LANGUAGE_TABLE = "CREATE TABLE " + db.TABLE_NAME + " (" +
                db.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                db.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                db.COLUMN_LABEL + " TEXT UNIQUE NOT NULL " +
                ");";
        AresLog.d(LOG_TAG, "SQL_CREATE_VALID_LANGUAGE_TABLE: " + SQL_CREATE_VALID_LANGUAGE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_VALID_LANGUAGE_TABLE);
    }

    public static void dropValidLanguageTable(SQLiteDatabase sqLiteDatabase) {
        AresLog.d(LOG_TAG, "dropValidLanguageTable");

        final String SQL_DROP_VALID_LANGUAGE_TABLE = "DROP TABLE IF EXISTS " + db.TABLE_NAME;
        AresLog.d(LOG_TAG, "SQL_DROP_VALID_LANGUAGE_TABLE: " + SQL_DROP_VALID_LANGUAGE_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_VALID_LANGUAGE_TABLE);
    }

    public static void addValidLanguages(SQLiteDatabase sqLiteDatabase) {
        final String SQL_INSERT_VALID_LANGUAGE_EN = "INSERT INTO " + db.TABLE_NAME + " (" +
                db.COLUMN_ID + ", " +
                db.COLUMN_CODE + ", " +
                db.COLUMN_LABEL + ") " +
                "VALUES (1, 'en', 'English');";
        AresLog.d(LOG_TAG, "SQL_INSERT_VALID_LANGUAGE_EN: " + SQL_INSERT_VALID_LANGUAGE_EN);

        final String SQL_INSERT_VALID_LANGUAGE_RU = "INSERT INTO " + db.TABLE_NAME + " (" +
                db.COLUMN_ID + ", " +
                db.COLUMN_CODE + ", " +
                db.COLUMN_LABEL + ") " +
                "VALUES (2, 'ru', 'Русский');";
        AresLog.d(LOG_TAG, "SQL_INSERT_VALID_LANGUAGE_RU: " + SQL_INSERT_VALID_LANGUAGE_RU);

        final String SQL_INSERT_VALID_LANGUAGE_UK = "INSERT INTO " + db.TABLE_NAME + " (" +
                db.COLUMN_ID + ", " +
                db.COLUMN_CODE + ", " +
                db.COLUMN_LABEL + ") " +
                "VALUES (3, 'uk', 'Українська');";
        AresLog.d(LOG_TAG, "SQL_INSERT_VALID_LANGUAGE_UK: " + SQL_INSERT_VALID_LANGUAGE_UK);

        sqLiteDatabase.execSQL(SQL_INSERT_VALID_LANGUAGE_EN);
        sqLiteDatabase.execSQL(SQL_INSERT_VALID_LANGUAGE_RU);
        sqLiteDatabase.execSQL(SQL_INSERT_VALID_LANGUAGE_UK);
    }
}
