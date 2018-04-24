package demo.projectZero;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.long1.projectZero.R;

public class DistanceActivity extends AppCompatActivity {

    Button btnStart;
    Button btnFinish;
    TextView tvStart;
    TextView tvFinish;
    TextView tvDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        tvStart = (TextView) findViewById(R.id.tvStart);
        tvFinish = (TextView) findViewById(R.id.tvFinish);
        tvDistance = (TextView) findViewById(R.id.tvDistance);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            double lon = location.getLongitude();
                            double lat = location.getLatitude();
                            tvStart.setText(String.valueOf(lon) + ", " + String.valueOf(lat));
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
                    });
        } catch (SecurityException e) {
            Log.e("Security Exception", e.getMessage());
        }

    }

}
