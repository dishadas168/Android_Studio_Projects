package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> family = new ArrayList<>();
        family.add("Gugusi");
        family.add("Bapa");
        family.add("Budhi");

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);

        try {
            sharedPreferences.edit().putString("family", new ObjectSerializer().serialize(family)).apply();
            Log.i("family", new ObjectSerializer().serialize(family));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("new family", sharedPreferences.getString("family", ""));

        try {
            ArrayList<String> newFamily = (ArrayList<String>) new ObjectSerializer().deserialize(sharedPreferences.getString("family", new ObjectSerializer().serialize(new ArrayList<String>())));
            Log.i("new family", newFamily.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}