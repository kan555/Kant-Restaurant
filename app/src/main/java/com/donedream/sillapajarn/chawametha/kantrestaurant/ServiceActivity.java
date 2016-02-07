package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //explicit
    private TextView shownameTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString,deskString, foodString, amountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //bind widget
        bindwidget();

        //show view
        showView();

        //show desk
        showDesk();

        //show menu
        showMenuFood();

    }   //main method

    private void showMenuFood() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.food_TABLE, null);

        int intCount = objCursor.getCount();
        String[] foodStrings = new String[intCount];
        String[] priceStrings = new String[intCount];
        String[] sourceStrings = new String[intCount];

        objCursor.moveToFirst();
        for (int i = 0; i < intCount; i++) {

            foodStrings[i] = objCursor.getString(objCursor.getColumnIndex(MyManage.colum_food));
            priceStrings[i] = objCursor.getString(objCursor.getColumnIndex(MyManage.colum_price));
            sourceStrings[i] = objCursor.getString(objCursor.getColumnIndex(MyManage.colum_source));

            objCursor.moveToNext();
        }   //for
        objCursor.close();

        MyAdapter objMyAdapter = new MyAdapter(ServiceActivity.this, foodStrings, priceStrings, sourceStrings);
        foodListView.setAdapter(objMyAdapter);

    }

    private void showDesk() {

        final String[] showDeskStrings = {"Desk 1", "Desk 2", "Desk 3", "Desk 4", "Desk 5",
                "Desk 6", "Desk 7", "Desk 8", "Desk 9", "Desk 10"};
        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, showDeskStrings);
        deskSpinner.setAdapter(deskAdapter);
        //select can change select which
        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deskString = showDeskStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                deskString = showDeskStrings[0];
            }
        });

    }   //show desk

    private void showView() {

        //receive from intent
        officerString = getIntent().getStringExtra("Name");
        shownameTextView.setText(officerString);

    }

    private void bindwidget() {
        shownameTextView = (TextView) findViewById(R.id.textView2);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

}   //main class
