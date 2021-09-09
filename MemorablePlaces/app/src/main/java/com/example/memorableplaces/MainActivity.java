package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> locations;
    public static ArrayAdapter<String> adapter;
    public static List<Double> latitudes = new ArrayList<Double>();
    public static List<Double> longitudes = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        locations = new ArrayList<String>();
        latitudes = new ArrayList<Double>();
        longitudes = new ArrayList<Double>();

        locations.add("Add a new place...");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if(position == 0){
                    intent = new Intent(getBaseContext(), NewLocationActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getBaseContext(), SavedLocationActivity.class);
                    intent.putExtra("Latitude", latitudes.get(position-1));
                    intent.putExtra("Longitude", longitudes.get(position-1));
                    intent.putExtra("Address", locations.get(position));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                double lat = intent.getDoubleExtra("Latitude", 0);
                double lon = intent.getDoubleExtra("Longitude", 0);
                latitudes.add(lat);
                longitudes.add(lon);

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
                    String address = "";
                    if(!addresses.isEmpty() && addresses.size()>0){
                        if(addresses.get(0).getFeatureName() != null)
                            address += addresses.get(0).getFeatureName();
                        if(addresses.get(0).getCountryName() != null)
                            address += addresses.get(0).getCountryName();
                    }else{
                        address = new SimpleDateFormat("HH:mm yyyy-MM-dd ").format(new Date());
                    }
                    locations.add(address);
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//onActivityResult

}