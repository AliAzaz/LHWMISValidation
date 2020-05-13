package utils;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

/**
 * Created by Umeed-e-Nau on 12/21/2016.
 */
public class GpsLocationTracker {

    Context mContext;
    LocationManager locationManager;
    LocationListener locationListener;
    boolean isGpsEnabled, isNetworkLocationEnabled;
    double mAltitude = 0;
    double mLongitude = 0;

    public GpsLocationTracker(Context context) {
        this.mContext = context;
        locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    private void startTracking() {

        // check whether GPS and NetworkBasedLocation is enable..
        isGpsEnabled =
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkLocationEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // on location receiving
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {

                // New Location
                mAltitude = location.getAltitude();
                mLongitude = location.getLongitude();

/*             Toast.makeText(mContext, "NETwork " + location.getAltitude() + " and " +
                        location.getLongitude(), Toast.LENGTH_SHORT).show(); */
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }

        };


        if (!isNetworkLocationEnabled && !isGpsEnabled) {
            // BOTH SOURCES ARE DISABLED
            // CAN'T TRACK LOCATION..

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("GPS Tracker");
            builder.setMessage("Please turn on GPS tracking from phone settings and RETRY ");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

        } else if (isNetworkLocationEnabled) {
            // Tracking location via Network

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);
        } else if (isGpsEnabled) {
            //Tracking location via GPS
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }


    }

    public String getLocation() {
        startTracking();
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Location loc2 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        String location = "";
        if (loc == null)
            location = "NETWORK: NULL";
        else
            location = "NETWORK: " + loc.getAltitude() + " " + loc.getLongitude();

        if (loc2 == null)
            location = location + " GPS: NULL";
        else
            location = location + " GPS:" + loc2.getAltitude() + " " + loc2.getLongitude();
        return location;
    }

    private void stopTracking() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);

        //  }

    }
}
