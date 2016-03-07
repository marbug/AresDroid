package com.github.vavooon.aresdroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.github.vavooon.aresdroid.entity.AresValidLanguage;

import java.util.ArrayList;

public class AresDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ares.droid.db";
    public static final String LOG_TAG = AresDbHelper.class.getSimpleName();

    // If you change the database schema, you must increment the database version here:
    public static final int DATABASE_VERSION = 1;

    public AresDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        AresLog.d(LOG_TAG, "constructor");
    }

    public static final class DbVersion implements BaseColumns {
        public static final String TABLE_NAME = "db_version";

        public static final String COLUMN_ID = "db_version_id";
        public static final String COLUMN_NUMBER = "db_version_number";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_NUMBER = COLUMN_INDEX_ID + 1;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        AresLog.d(LOG_TAG, "onCreate");

        this.createDbVersionTable(sqLiteDatabase);
        this.addDbVersion(sqLiteDatabase);

        AresValidLanguage.createValidLanguageTable(sqLiteDatabase);
        AresValidLanguage.addValidLanguages(sqLiteDatabase);
    }

    public void createDbVersionTable(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_DB_VERSION_TABLE = "CREATE TABLE " + DbVersion.TABLE_NAME + " (" +
                DbVersion.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                DbVersion.COLUMN_NUMBER + " INTEGER NOT NULL " +
                ");";
        AresLog.d(LOG_TAG, "SQL_CREATE_DB_VERSION_TABLE: " + SQL_CREATE_DB_VERSION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DB_VERSION_TABLE);
    }

    public void dropDbVersionTable(SQLiteDatabase sqLiteDatabase) {
        AresLog.d(LOG_TAG, "dropDbVersionTable");

        final String SQL_DROP_DB_VERSION_TABLE = "DROP TABLE IF EXISTS " + DbVersion.TABLE_NAME;
        AresLog.d(LOG_TAG, "SQL_DROP_DB_VERSION_TABLE: " + SQL_DROP_DB_VERSION_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_DB_VERSION_TABLE);
    }

    public void addDbVersion(SQLiteDatabase sqLiteDatabase) {
        final String SQL_INSERT_DB_VERSION_EN = "INSERT INTO " + DbVersion.TABLE_NAME + " (" +
                DbVersion.COLUMN_ID + ", " +
                DbVersion.COLUMN_NUMBER + ") " +
                "VALUES (1, ?);";
        AresLog.d(LOG_TAG, "SQL_INSERT_DB_VERSION_EN: " + SQL_INSERT_DB_VERSION_EN);

        sqLiteDatabase.execSQL(SQL_INSERT_DB_VERSION_EN, new String[] {
                Integer.toString(DATABASE_VERSION)
        });
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        AresLog.d(LOG_TAG, "onUpgrade");

        // TODO: alter everything instead of recreation
        AresValidLanguage.dropValidLanguageTable(sqLiteDatabase);
        this.dropDbVersionTable(sqLiteDatabase);

        this.onCreate(sqLiteDatabase);
    }

    public int getDbVersion() {
        AresLog.d(LOG_TAG, "getDbVersion");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbVersion.TABLE_NAME, null);

        int version = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            version = cursor.getInt(DbVersion.COLUMN_INDEX_NUMBER);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return version;
    }

    public ArrayList<AresValidLanguage> getValidLanguages() {
        AresLog.d(LOG_TAG, "getValidLanguages");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + AresValidLanguage.db.TABLE_NAME, null);

        ArrayList<AresValidLanguage> validLanguages = new ArrayList<AresValidLanguage>(0);

        int numberOfRows = cursor.getCount();
        if (numberOfRows > 0) {
            for (int i = 0; i < numberOfRows; i++) {
                cursor.moveToPosition(i);

                AresValidLanguage validLanguage = new AresValidLanguage(
                        cursor.getInt(AresValidLanguage.db.COLUMN_INDEX_ID),
                        cursor.getString(AresValidLanguage.db.COLUMN_INDEX_CODE),
                        cursor.getString(AresValidLanguage.db.COLUMN_INDEX_LABEL)
                );
                validLanguages.add(validLanguage);
            }
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return validLanguages;
    }
}
