package com.piworks.build1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.piworks.build1.popup.MyPREFERENCES;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private DrawerLayout drawer;
    TextView username_navbar;
    String username;
    String expectedamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_navbar =(TextView)findViewById(R.id.nav_username);



        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ///////////////
        navigationView.bringToFront();                         // DO  NOT FORGET TO INCLUDE THIS LINE ELSE DRAWER WONT BE CLICKABLE
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new today_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_today);

        }

    }
        @Override
                public void onBackPressed(){
                if (drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawer(GravityCompat.START);
                }
                else {
                    super.onBackPressed();           // This does the genenal work on back pressed
                                                    // if want to make changes , do here
                }

        }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        username = prefs.getString("name", "username");
        expectedamount = prefs.getString("expectedP","0.0");


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        username_navbar= (TextView) headerView.findViewById(R.id.nav_username);
        username_navbar.setText(username);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_today:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new today_fragment()).commit();
                //navigationView.setCheckedItem(R.id.nav_today);
                break;
            case R.id.nav_analysis:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new analysis_fragment()).commit();
               // navigationView.setCheckedItem(R.id.nav_analysis);
                break;
            case R.id.nav_about:
                Toast.makeText(this,"In future updates",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this,"In future updates",Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
