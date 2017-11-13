package com.livos.companionplants.data.local.database.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.livos.companionplants.data.local.database.model.DaoMaster;
import com.livos.companionplants.util.ApplicationScope;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@ApplicationScope
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    //private static final String DATABASE_NAME = "plants.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseOpenHelper(Context context, String databaseFileName) {
        super(context, databaseFileName, null, DATABASE_VERSION);
    }
}