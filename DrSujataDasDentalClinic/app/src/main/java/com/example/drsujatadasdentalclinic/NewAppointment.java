package com.example.drsujatadasdentalclinic;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewAppointment extends AppCompatActivity {

    ListView searchPatientsListView;
    public static ArrayAdapter<String> adapter;
    public static ArrayList<String> patients;

    public void addNewPatient(View view){
        Intent intent = new Intent(this, AddNewAppointment.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchPatientsListView = (ListView) findViewById(R.id.searchPatientsListView);
        patients = new ArrayList<String>();

        MainActivity.db.populatePatients();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patients);
        searchPatientsListView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(patients.contains(query)){
                    adapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchPatientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ITEM NUM", String.valueOf(view.getId()));
            }
        });
        

    }
}