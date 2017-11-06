package com.example.android.contentprovidersample.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Shereen on 11/6/2017.
 */

public class TestContentProvider extends ContentProvider {
    TestDBHelper dbHelper;
    final static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI("com.example.android.contentprovidersample", "testResults", 1);
        mUriMatcher.addURI("com.example.android.contentprovidersample", "testResults/#", 2);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new TestDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sort) {
        int code = mUriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (code) {
            case 1:
                return db.query(TestContract.TestResultsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sort);
            case 2:
                selection = TestContract.TestResultsEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.query(TestContract.TestResultsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sort);
            default:
                throw new IllegalArgumentException("Error Cannot resolve the uri" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int code = mUriMatcher.match(uri);
        if (code == 1) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert(TestContract.TestResultsEntry.TABLE_NAME, null, contentValues);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
