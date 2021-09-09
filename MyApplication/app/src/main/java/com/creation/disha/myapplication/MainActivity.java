package com.creation.disha.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert (View view){

        EditText dollar = (EditText) findViewById(R.id.dollar);
        Double pound = 0.77 * Double.parseDouble(dollar.getText().toString());
        Toast.makeText(this, "£" + pound.toString(), Toast.LENGTH_SHORT).show();

    }
}
