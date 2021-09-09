package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void convert (View view){
        EditText number = (EditText) findViewById(R.id.numberEditText);

        if (number.getText().toString().matches("")){
            Toast.makeText(this, "Enter amount in USD!", Toast.LENGTH_SHORT).show();

        }else{

            double rateUSDtoINR = 72.45;
            double convertedNumber = Double.parseDouble(number.getText().toString()) * rateUSDtoINR;
            Toast.makeText(this, "$" + number.getText().toString()
                    + " is Rs " + String.format("%.2f",convertedNumber), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}