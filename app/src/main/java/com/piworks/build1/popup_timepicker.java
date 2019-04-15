package com.piworks.build1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class popup_timepicker extends Activity {
    EditText e1,e2;
    static String username_popup ;
    static String expectedamount ;
    SharedPreferences mysharedpref;

    TimePicker timePicker ;
    Button b1;
    int hourvalue;
    int minutevalue;



    public static final String MyPREFERENCES = "MyPrefs" ;


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_timepicker);
        timePicker = (TimePicker)findViewById(R.id.simpleTimePicker);
        b1 = (Button)findViewById(R.id.timepicker_ok);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*(.75)),(int) (height*(.65)));


        hourvalue = timePicker.getHour();
        minutevalue  = timePicker.getMinute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mysharedpref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = mysharedpref.edit();
        //username_popup = e1.getText().toString();
        //expectedamount = e2.getText().toString();
       // editor.putString("name",username_popup);
      //  editor.putString("expectedP",expectedamount);
      //  editor.apply();
        editor.putInt("hourvalue",hourvalue);
        editor.putInt("minutevalue",minutevalue);
    }

    public void dosave(View view) {
        finish();

    }
}
