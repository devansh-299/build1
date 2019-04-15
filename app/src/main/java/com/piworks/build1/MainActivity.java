package com.piworks.build1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
    TextView today_amount ;
    String amount_value = "0";
    mydatabase mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username_navbar =(TextView)findViewById(R.id.nav_username);
        today_amount = (TextView)findViewById(R.id.today_amount );


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

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = prefs.getString("name", "username");
        expectedamount = prefs.getString("expectedP", "0.0");


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        username_navbar = (TextView) headerView.findViewById(R.id.nav_username);
        username_navbar.setText(username);




        ///////////////// NOtification Part!!!!//////////////////////////////




        int minutevalue = prefs.getInt("minutevalue",0);
        int hourvalue = prefs.getInt("hourvalue",0);

        int timemillis = (hourvalue*(60*60*1000)) + (minutevalue*(60*1000));
        Intent iN = new Intent();
        iN.setAction("piworks.build1.reminder");
        PendingIntent pd = PendingIntent.getBroadcast(this,1,iN,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timemillis,AlarmManager.INTERVAL_DAY,pd);


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
                final AlertDialog.Builder a1 = new AlertDialog.Builder(this);
                a1.setTitle("Exit");
                a1.setMessage("Are you Sure?");
                a1.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                moveTaskToBack(true);
                            }
                        }


                );
                a1.setNegativeButton("No" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                a1.show();
                a1.setCancelable(false);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this,settings.class));
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void do_enter(View view) {
        startActivity(new Intent(MainActivity.this,popup_get_amount.class));
    }


    public String getMyData() {
        ////////// Database part BELOW!! /////


        mydatabase = new mydatabase(this);
        return mydatabase.getvalue();
    }
}
