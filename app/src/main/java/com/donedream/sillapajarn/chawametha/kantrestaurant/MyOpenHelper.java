package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WINDOWS7 on 6/2/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "Restaurant.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "{asswprd text," +
            "Name text);";

    private static final String crate_food_table = "create table foodTABLE (" +
            "_id integer primary key," +
            "Food text," +
            "Price text," +
            "Source text);";



    public MyOpenHelper(Context context ) {
        //super ทำcontextไปหาSQLiteก่อน แล้ว dbName then null then dbVersion
        super(context, database_name, null, database_version);
    }   //Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user_table);
        db.execSQL(crate_food_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   // Main Class
