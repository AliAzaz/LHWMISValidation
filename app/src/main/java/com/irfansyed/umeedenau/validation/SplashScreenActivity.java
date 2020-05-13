package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import utils.MyPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_splash_screen);

// Clear Old Data Irfan



        final MyPreferences preferences = new MyPreferences(this);
    //    if (preferences.getReq1() == null) {


        // preferences.setReq1("http://43.245.131.159/umeed/Testing/Monitoring");
           // preferences.setReqLogin("http://10.199.5.216:61259///Testing/check_user_id");

           //preferences.setReqLogin("https://vcoe1.aku.edu/LHWMIS/Testing/check_user_id");
            preferences.setReqLogin("https://vcoe1.aku.edu/LHWMIS/Testing/check_user_id");
            preferences.setReq1("https://vcoe1.aku.edu/LHWMIS/Testing/InsertLHWSection");
            preferences.setReq2("https://vcoe1.aku.edu/LHWMIS/Testing/InsertF1F2SectionC");
            preferences.setReq3("https://vcoe1.aku.edu/LHWMIS/Testing/");


           // preferences.setReq1("http://10.199.5.216:61259///Testing/InsertLHWSection");
           // preferences.setReq2("http://10.199.5.216:61259///Testing/InsertF1F2SectionC");
           // preferences.setReq3("http://10.199.5.216:61259///Testing/");


        preferences.setAppVersion("11");

     //   }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

// show login or main page
                Intent mainIntent;
                if (preferences.getUserId() == -1) {
                    //mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                } else {
                  //  mainIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                }
             mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);

          // mainIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);


                    SplashScreenActivity.this.startActivity(mainIntent);
                    SplashScreenActivity.this.finish();
            }
        }, 1000);
    }



}
