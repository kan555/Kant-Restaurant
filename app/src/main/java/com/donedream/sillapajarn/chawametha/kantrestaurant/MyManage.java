package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WINDOWS7 on 6/2/2559.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase wriSqLiteDatabase, readSqLiteDatabase;

    public static final String user_TABLE = "userTABLE";
    public static final String food_TABLE = "foodTABLE";
    public static final String colum_id = "_id";
    public static final String colum_user = "User";
    public static final String colum_pass = "Password";
    public static final String colum_name = "Name";
    public static final String colum_food = "Food";
    public static final String colum_price = "Price";
    public static final String colum_source = "Source";

    public MyManage(Context context) {

        //Create and Connect Database
        objMyOpenHelper = new MyOpenHelper(context);
        wriSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] searchUser(String strUser) {

        try {

            String[] resultStrings = null;
            // explicit for ram
            Cursor objCursor = readSqLiteDatabase.query(user_TABLE,
                    new String[]{colum_id,colum_user,colum_pass,colum_name},
                    colum_user + "=?",
                    new String[]{String.valueOf(strUser)},
                    null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    resultStrings = new String[4];
                    for (int i = 0; i < 4; i++) {
                        resultStrings[i] = objCursor.getString(i);
                    }

                }   //if2
            }   //if1
            objCursor.close();
            return resultStrings;

        } catch (Exception e) {
            return null;
        }   //try

        //return new String[0];
    }


    public long addNewValue(int intTable,
                            String strColumn2,
                            String strColumn3,
                            String strColumn4) {

        ContentValues objContentValues = new ContentValues();
        long longReturn = 0;

        switch (intTable) {
            case 0:

                objContentValues.put(colum_user, strColumn2);
                objContentValues.put(colum_pass, strColumn3);
                objContentValues.put(colum_name, strColumn4);
                wriSqLiteDatabase.insert(user_TABLE, null, objContentValues);

                break;
            case 1:
                objContentValues.put(colum_food, strColumn2);
                objContentValues.put(colum_price, strColumn3);
                objContentValues.put(colum_source, strColumn4);
                wriSqLiteDatabase.insert(food_TABLE, null, objContentValues);
                break;
        }   //switch

        return longReturn;
    }

}   // Main Class
