package com.piworks.build1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.piworks.build1.popup.MyPREFERENCES;

public class settings extends AppCompatActivity {
    TextView username_settings;

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
}
