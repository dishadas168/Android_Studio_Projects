package com.example.memorableplaces;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MainActivity.latitudes.add(latLng.latitude);
                MainActivity.longitudes.add(latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
                Toast.makeText(NewLocationActivity.this, "Location saved", Toast.LENGTH_LONG).show();

                Geocoder geocoder = new Geocoder(NewLocationActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String address = "";
                    if(!addresses.isEmpty() && addresses.size()>0){
                        if(addresses.get(0).getFeatureName() != null)
                            address += addresses.get(0).getFeatureName();
                        if(addresses.get(0).getCountryName() != null)
                            address += addresses.get(0).getCountryName();
                    }else{
                        address = new SimpleDateFormat("HH:mm yyyy-MM-dd ").format(new Date());
                    }
                    MainActivity.locations.add(address);
                    MainActivity.adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}