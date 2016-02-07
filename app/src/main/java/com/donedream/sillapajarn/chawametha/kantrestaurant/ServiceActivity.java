package com.donedream.sillapajarn.chawametha.kantrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }   //main method

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
