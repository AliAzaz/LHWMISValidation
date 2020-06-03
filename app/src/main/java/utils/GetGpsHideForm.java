package utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;

public class GetGpsHideForm {

    public static LocationHelper.LocationResult locationResult;
    public static LocationHelper locationHelper;
    public static ProgressDialog progressBar;
    public static double latitude;
    public static double longitude;

    public static String get_gps(Context mContext) {


        // to get location updates, initialize LocationResult
        locationResult = new LocationHelper.LocationResult() {
            @Override
            public void gotLocation(Location location) {

                //Got the location!
                if (location != null) {

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();


                    locationHelper.stopGettingLocationUpdates();
                    // here you can save the latitude and longitude values
                    // maybe in your text file or database
                    progressBar.dismiss();


                } else {
                    //Toast.makeText(getApplicationContext.this,"Kindly Turn ON Device GPS",Toast.LENGTH_LONG).show();
                    progressBar.dismiss();


                }

            }

        };

        locationHelper = new LocationHelper();


        progressBar = new ProgressDialog(mContext);
        progressBar.setMessage("Searching for GPS coordinates...");
        progressBar.setCancelable(false);
        progressBar.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

            }
        });
        progressBar.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                locationHelper.stopGettingLocationUpdates();
                dialog.dismiss();
            }
        });

        progressBar.show();


        locationHelper.getLocation(mContext, locationResult);

        return latitude + "/" + longitude;

    }


}
