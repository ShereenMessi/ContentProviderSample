package com.example.android.contentprovidersample;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.contentprovidersample.data.TestContract;
import com.example.android.contentprovidersample.data.TestDBHelper;

public class MainActivity extends AppCompatActivity {
    TestDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbHelper = new TestDBHelper(this);
        insert("Ali", 100);
        Cursor cursor = readData();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(TestContract.TestResultsEntry.COLUMN_STUDENT_NAME));
            double result = cursor.getDouble(cursor.getColumnIndex(TestContract.TestResultsEntry.COLUMN_RESULT));
            Log.v("Table Results", "Name " + name + ", Result " + result);
        }
        cursor.close();
    }

    public void insert(String name, double result) {
        ContentValues values = new ContentValues();
        values.put(TestContract.TestResultsEntry.COLUMN_STUDENT_NAME, name);
        values.put(TestContract.TestResultsEntry.COLUMN_RESULT, result);
        getContentResolver().insert(TestContract.TestResultsEntry.RESULTS_URI, values);
//        if (RowId == -1) {
//            Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show();
//
//        }

    }


    public Cursor readData() {
        Cursor cursor = getContentResolver().query(TestContract.TestResultsEntry.RESULTS_URI, null, null, null, null, null);
        return cursor;
    }
}