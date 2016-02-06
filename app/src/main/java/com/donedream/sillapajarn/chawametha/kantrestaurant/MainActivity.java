package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    }   // Main Method

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
