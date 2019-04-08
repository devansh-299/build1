package com.piworks.build1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

public class popup extends Activity {
    EditText e1,e2;
    static String username_popup ;
    static String expectedamount ;
    SharedPreferences mysharedpref;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);
        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.editText2);




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*(.75)),(int) (height*(.5)));
    }

    public void dosomething(View view) {

        mysharedpref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = mysharedpref.edit();
        username_popup = e1.getText().toString();
        expectedamount = e2.getText().toString();
        editor.putString("name",username_popup);
        editor.putString("expectedP",expectedamount);
        editor.apply();
        startActivity(new Intent(this,MainActivity.class));               // for closing the popup
    }

    @Override
    protected void onResume() {
        super.onResume();
        mysharedpref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = mysharedpref.edit();
        username_popup = e1.getText().toString();
        expectedamount = e2.getText().toString();
        editor.putString("name",username_popup);
        editor.putString("expectedP",expectedamount);
        editor.apply();

    }
}
