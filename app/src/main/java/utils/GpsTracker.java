package utils;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

/**
 * Created by Umeed-e-Nau on 12/22/2016.
 */


public class GpsTracker implements ProgressDialog.OnDismissListener {
    Context mContext;
    Location loc;
    LocationManager locationManager;
    LocationListener listener;
    ProgressDialog progressBar;
    String gpsData = "";
    int count = 0;

    public GpsTracker(Context context) {
        this.mContext = context;
    }

    public void requestLocationUpdate(final LocationCallback callback) {
        loc = null;

        turnGPSOn();

        progressBar = new ProgressDialog(mContext);
        progressBar.setMessage("Searching for GPS coordinates...");
        progressBar.setCancelable(false);
        progressBar.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (gpsData.length() == 0)
                    showGPSAlert();
            }
        });
        progressBar.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progressBar.show();

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

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

        boolean networkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (networkEnable) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1, 1, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            if (location != null) {
                                loc = location;
                                gpsData = loc.getLatitude() + "-" + loc.getLongitude();

                                Toast.makeText(mContext, "GPS coordinates : " + gpsData, Toast.LENGTH_SHORT).show();

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

                                locationManager.removeUpdates(this);
                                location = null;

                                progressBar.dismiss();
                            } else {
                                Toast.makeText(mContext, "Can't track GPS Coordinates", Toast.LENGTH_SHORT).show();
                            }
                            callback.onNewLocation(gpsData);
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
                    });

        } else {

            turnGPSOn();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1, 1, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            if (location != null) {
                                loc = location;
                                gpsData = loc.getLatitude() + "-" + loc.getLongitude();

                                Toast.makeText(mContext, "GPS coordinates : " + gpsData, Toast.LENGTH_SHORT).show();

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

                                locationManager.removeUpdates(this);
                                location = null;

                                turnGPSOff();
                                progressBar.dismiss();
                            } else {
                                Toast.makeText(mContext, "Can't track GPS Coordinates", Toast.LENGTH_SHORT).show();
                            }
                            callback.onNewLocation(gpsData);
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
                    });
        }
        startThread();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (gpsData.length() == 0)
            showGPSAlert();
    }

    public interface LocationCallback {
        void onNewLocation(String gpsData);
    }

    public void startThread() {
        count = 0;

        new Thread() {
            public void run() {
                while (count != 20) {

                    try {
                        Thread.sleep(1000);
                        count++;
                        System.out.println("waiting...");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                progressBar.dismiss();
            }
        }.start();
    }


    public void turnGPSOn() {

        String provider = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { // if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings",
                    "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            mContext.sendBroadcast(poke);
        }
    }

    private void turnGPSOff() {

        String provider = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps")) { // if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings",
                    "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));

            mContext.sendBroadcast(poke);
        }
    }

    public void showGPSAlert() {
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setTitle("Retry");
        b.setMessage("GPS coordinates are not received yet. Do you want to continue searching?");
        b.setCancelable(false);
        b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                progressBar = new ProgressDialog(mContext);
                progressBar
                        .setMessage("Searching for GPS coordinates...");
                progressBar.setCancelable(false);

                progressBar.setOnDismissListener(GpsTracker.this);
                progressBar.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                progressBar.show();
                startThread();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                turnGPSOff();
            }
        });
        b.show();

    }


}
