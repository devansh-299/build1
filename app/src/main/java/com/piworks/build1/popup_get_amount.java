package com.piworks.build1;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class popup_get_amount extends Activity {
    EditText e1;

    static String amnt_value;
    static  String current_date;


    mydatabase mydatabase;              // object of our database class
    /*
    static String expectedamount ;
    SharedPreferences mysharedpref;
    public static final String MyPREFERENCES = "MyPrefs" ; */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_getamnt_layout);
        e1 = (EditText) findViewById(R.id.get_amount_value);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * (.75)), (int) (height * (.5)));
    }

    public void dosomething(View view) {
/*
        mysharedpref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = mysharedpref.edit();
        */
        amnt_value = e1.getText().toString();

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        current_date = formatter.format(date);

        mydatabase = new mydatabase(this);
        boolean isInserted = mydatabase.insertData(current_date,amnt_value);
        if (isInserted == true) {
            Toast.makeText(this, "Amount saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Amount not saved", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }

}
