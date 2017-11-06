package com.example.android.contentprovidersample.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shereen on 11/6/2017.
 */

public class TestContract {
    public final static Uri BASE_URI=Uri.parse("content://com.example.android.contentprovidersample");
    public final static class TestResultsEntry implements BaseColumns{
        public final static String TABLE_NAME="testResults";

        public final static String COLUMN_ID=BaseColumns._ID;
        public final static String COLUMN_STUDENT_NAME="student_name";
        public final static String COLUMN_RESULT="result";

        public final static Uri RESULTS_URI=Uri.withAppendedPath(BASE_URI,TABLE_NAME);


    }
}
