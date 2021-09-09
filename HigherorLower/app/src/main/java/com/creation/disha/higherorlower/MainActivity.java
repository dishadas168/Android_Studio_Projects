package com.creation.disha.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomNumber();
    }

    public void generateToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void generateRandomNumber(){
        int number = new Random().nextInt(21) + 1;
        randomNumber =  number;
    }

    public void guess (View view){
        EditText guess = (EditText) findViewById(R.id.number);
        int guessNum = Integer.parseInt(guess.getText().toString());

        if(guessNum > randomNumber){
            generateToast("It's lower!");
        }else if (guessNum < randomNumber){
            generateToast("It's higher!");
        }else {
            generateToast("That's right! Try again.");
            generateRandomNumber();
        }
    }
}
