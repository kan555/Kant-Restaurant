package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage objMyManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request Database
        objMyManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //clean data
        cleanData();

        //synchronized JSON to SQLite
        synJSONtoSQLite();

    }   // Main Method

    private void synJSONtoSQLite() {

        //change policy for and to connect to http directly
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTable = 1;
        String tag = "Restaurant"

        while (intTable <= 2) {

            //1. create inputStream
            InputStream objInputStream = null;
            String StrURLuser = "http://swiftcodingthai.com/6feb/php_get_data_kant.php";
            String StrURLfppd = "http://swiftcodingthai.com/6feb/php_get_data_food.php";

            try {

            } catch (Exception e) {
                Log.d(tag, "ImputStream ==> " + e.toString());
            }

            //2. create JSON String

            //3.Update SQLite


            intTable += 1;
        }   //while

    }   // synJSONtoSQLite

    private void cleanData() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(MyManage.food_TABLE, null, null);
        objSqLiteDatabase.delete(MyManage.user_TABLE, null, null);

    }   //clean data

    private void testAddValue() {

        for (int i = 0; i <= 1; i++) {
            objMyManage.addNewValue(i, "test1", "test2", "test3");
        }   // for

    }   //test add value

}      // Main Class
