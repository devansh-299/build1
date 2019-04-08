package com.piworks.build1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static com.piworks.build1.popup.MyPREFERENCES;


public class startscreen extends AppCompatActivity {


    String username;
    String expectedamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        username = prefs.getString("name", "username");
        expectedamount = prefs.getString("expectedP","0.0");



        if ((username == "username") && (expectedamount == "0.0")) {
            startActivity(new Intent(startscreen.this,popup.class));
            username = prefs.getString("name", "username");
            expectedamount = prefs.getString("expectedP","0.0");

        }
        else {
            startActivity(new Intent(startscreen.this,MainActivity.class));
            finish();
        }

    }
}
