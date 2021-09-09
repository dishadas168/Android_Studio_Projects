package com.example.drsujatadasdentalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    protected static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.patientDataListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        launchNewAppointment();
                        break;
                    case 1:
                        Log.i("1", String.valueOf(position));
                        break;
                    case 2:
                        Log.i("2", String.valueOf(position));
                        break;
                    case 3:
                        Log.i("3", String.valueOf(position));
                        break;
                }
            }
        });
    }

    public void launchNewAppointment(){
        Intent intent = new Intent(this, NewAppointment.class);
        startActivity(intent);
    }

}