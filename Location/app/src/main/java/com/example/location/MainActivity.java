package com.example.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //map.put("time",location.getTime());
                //map.put("longitude",location.getLongitude());
                //map.put("latitude",location.getLatitude());
                //map.put("Altitude",location.getAltitude());
                loc = location;
                Log.d("LOcation::", location.toString());



                //Toast.makeText(this,message,Toast.LENGTH_LONG);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, locationListener);
            }
        }
    }

    public void getLoc(View view){
        TextView textView= (TextView) findViewById(R.id.textView);
        String showTextView = (String) textView.getText();

        List newList = new ArrayList();
        newList.add(String.valueOf(loc.getLatitude()));
        newList.add(String.valueOf(loc.getLongitude()));

        if (showTextView == ""){
            textView.setText(newList.toString());
        } else{
            List arrList = Arrays.asList(showTextView.split(","));
            if ((String.valueOf(arrList.get(0)).equals(String.valueOf(newList.get(0)))) &&
                    (String.valueOf(arrList.get(1)).equals(String.valueOf(newList.get(1))))){
                Toast toast = Toast.makeText(this, "Same Location", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT);
                toast.show();
            }
            textView.setText(android.text.TextUtils.join(",", newList));
        }

    }
}
