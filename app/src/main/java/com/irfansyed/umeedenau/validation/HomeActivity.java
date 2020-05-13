package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import data.LocalDataManager;
import utils.MyPreferences;

import static data.LocalDataManager.database;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView Profile_name;

    private  double Appversion=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MyPreferences preferences = new MyPreferences(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        TextView txtSummry = findViewById(R.id.txtSumry);

        txtSummry.setText("");




    }


    void select_tehsil() {



        int O_HH_C=0;
        int O_VHC_C=0;
        int O_WSG_C=0;

        int C_HH_C=0;
        int C_VHC_C=0;
        int C_WSG_C=0;





        String query = "select LHWOfficeHHCount,LHWCommunityHHCount,LHWOfficeVHCCount,LHWCommunityVHCCount,LHWOfficeWSGCount,LHWCommunityWSGCount from TableLHWSection ";


        query = String.format(query);

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String s_O_HH_C;
                    String s_O_VHC_C;
                    String s_O_WSG_C;
                    String s_C_HH_C;
                    String s_C_VHC_C;
                    String s_C_WSG_C;



                    s_O_HH_C=c.getString(0);
                    //s_O_VHC_C=c.getString();
                    s_O_HH_C=c.getString(0);
                    s_O_HH_C=c.getString(0);
                    s_O_HH_C=c.getString(0);


                } while (c.moveToNext());
            }
        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        int put_extr=0;
        final MyPreferences preferences = new MyPreferences(HomeActivity.this);

         if (id == R.id.upload_date) {
             intent = new Intent(this, PendingUploads.class);
        }

        if (id == R.id.upload_photo) {
            intent = new Intent(this, PhotoUploads.class);
        }

        if (id == R.id.stage_2) {
            intent = new Intent(this, LHWDashbord.class);
        }























     intent.putExtra("put_extra",put_extr);

        if (intent != null)
            startActivity(intent);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
