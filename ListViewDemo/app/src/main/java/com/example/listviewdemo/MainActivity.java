package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.listView);

        //Lists are good for putting items in Listview
        List<String> names = new ArrayList<String>();
        names.add("eenie");
        names.add("meenie");
        names.add("mynie");
        names.add("mo");

        //Adapter acts as a medium for taking a list and giving it a style
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, names);

        //This adapter(list with style) is set to listView view
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText( getApplicationContext(), names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}