package com.example.android.contentprovidersample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.contentprovidersample.data.TestContract.TestResultsEntry;

/**
 * Created by Shereen on 11/6/2017.
 */

public class TestDBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "test.db";
    private final static int DATABASE_VERSION = 1;

    public TestDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TestResultsEntry.TABLE_NAME +
                "(" + TestResultsEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                TestResultsEntry.COLUMN_STUDENT_NAME + " TEXT," +
                TestResultsEntry.COLUMN_RESULT + " REAL);";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
