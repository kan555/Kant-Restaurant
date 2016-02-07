package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.HttpCookie;
import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    //explicit
    private TextView shownameTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString,deskString, myfoodString, amountString;

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
        final String[] foodStrings = new String[intCount];
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

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                confirmOrder(foodStrings[i]);
            }
        });

    }   //show menu

    private void confirmOrder(String foodString) {
        myfoodString = foodString;
        CharSequence[] objCharSequences = {"1 จาน","2 จาน","3 จาน","4 จาน","5 จาน"};
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(foodString);
        objBuilder.setSingleChoiceItems(objCharSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                amountString = Integer.toString(i + 1);
                dialogInterface.dismiss();
                updateOrder();

            }
        });
        objBuilder.show();


    }   //confirmOrder

    private void updateOrder() {

        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("Officer", officerString));
            objNameValuePairs.add(new BasicNameValuePair("Desk", deskString));
            objNameValuePairs.add(new BasicNameValuePair("Food", myfoodString));
            objNameValuePairs.add(new BasicNameValuePair("item", amountString));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/6feb/php_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(ServiceActivity.this,
                    "Update Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(ServiceActivity.this,
                    "Cannot Update", Toast.LENGTH_SHORT).show();
        }

    }   //updateOrder


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
