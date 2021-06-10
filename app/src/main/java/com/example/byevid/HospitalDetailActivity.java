package com.example.byevid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.byevid.model.Hospital;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HospitalDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "Hospital Detail";

    private GoogleMap mMap;
    private ImageView btn_back;
    private Button btn_direction, btn_call;
    private TextView tx_name, tx_address, tx_distance;
    private ImageView img_thumb;

    private Hospital data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        tx_name = (TextView) findViewById(R.id.tv_hospital_detail_name);
        tx_address = (TextView) findViewById(R.id.tv_hospital_detail_address);
//        tx_distance = (TextView) findViewById(R.id.tv_hospital_detail_distance);
        img_thumb = (ImageView) findViewById(R.id.img_hospital_detail_thumb);

        // Runtime permission
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                }, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get intent data
        getIntentData();

        // Button back
        btn_back = (ImageView) findViewById(R.id.btn_hospital_detail_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Button direction
        btn_direction = (Button) findViewById(R.id.btn_hospital_detail_direction);
        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(HospitalDetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HospitalDetailActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    Intent direction = new Intent(android.content.Intent.ACTION_VIEW,
//                            Uri.parse("http://maps.google.com/maps/search/?api=1&query=" + Double.toString(data.get_lang()) + "&" + Double.toString(data.get_long())));
//                    startActivity(direction);
                }
            }
        });

        // Button call
        btn_call = (Button) findViewById(R.id.btn_hospital_detail_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HospitalDetailActivity.this, "Tel:" + data.getPhone(), Toast.LENGTH_SHORT).show();
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + data.getPhone()));
                startActivity(call);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_detail);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in point and move the camera
        int zoomLevel = 18;
//      LatLng point = new LatLng(-6.268603442648725, 106.97224453937066);
        LatLng point = new LatLng(data.get_lang(), data.get_long());
        mMap.addMarker(new MarkerOptions().position(point).title(data.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, zoomLevel));

//        mMap.setMyLocationEnabled(true);
    }

    private void getIntentData() {
        if (true) {
            // Retrieve object in second Activity
            data = (Hospital) getIntent().getSerializableExtra("Data");

            tx_name.setText(data.getName());
            tx_address.setText(data.getAddress());
//            tx_distance.setText(Float.toString(getDistance()) + " km");
            Glide.with(this)
                    .load(data.getImgUrl())
                    .fitCenter()
                    .centerCrop()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_thumb);
        } else {
            Log.w(TAG, "No intent data retrieved.");
        }
    }

    private float getDistance() {
        float[] results = new float[1];
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            Location.distanceBetween(latitude, longitude,
                    data.get_lang(), data.get_long(), results);
        }

        return results[0];
    }

}