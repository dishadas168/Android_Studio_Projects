package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int thoughtNumber;

    public void generateRandomNumber(){
        Random rand = new Random();
        thoughtNumber = rand.nextInt(20) + 1;
    }

    public void guessNumber(View view){
        EditText guessedNumberText = (EditText) findViewById(R.id.guessEditText);
        int guessedNumber = Integer.parseInt(guessedNumberText.getText().toString());
        guessedNumberText.getText().clear();

        String message;

        if( guessedNumber > thoughtNumber){
            message = "Lower!";
        }else if (guessedNumber < thoughtNumber){
            message = "Higher!";
        }else{
            message = "You got it! Let's try another round.";
            generateRandomNumber();
        }

        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();
    }
}