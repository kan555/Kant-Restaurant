package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WINDOWS7 on 6/2/2559.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase wriSqLiteDatabase, readSqLiteDatabase;

    public MyManage(Context context) {

        //Create and Connect Database
        objMyOpenHelper = new MyOpenHelper(context);
        wriSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

}   // Main Class
