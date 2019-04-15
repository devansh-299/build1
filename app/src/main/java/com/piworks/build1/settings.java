package com.piworks.build1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.piworks.build1.popup.MyPREFERENCES;

public class settings extends AppCompatActivity {
    TextView username_settings;
    static String timestring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        username_settings = (TextView)findViewById(R.id.settings_username);
    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String username = prefs.getString("name", "username");
        if (username != null){
            username_settings.setText(username);
        }

    }

    public void doselect_time(View view) {
        startActivity(new Intent(settings.this,popup_timepicker.class));


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
