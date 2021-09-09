package com.example.connect3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isRedTurn;
    int[] occupancy = new int[9];
    int[][] states = {{0,4,8},{2,4,6},{1,4,7},{3,4,5},{0,3,6},{2,5,8},{0,1,2},{6,7,8}};
    boolean gameActive = true;

    public void updateStates(ImageView button){
        int tag = Integer.parseInt(button.getTag().toString());
        int points;
        if(isRedTurn) points = 1;
        else points = -1;
        if(occupancy[tag] == 0 && gameActive) {
            occupancy[tag] += points;
            if(isRedTurn) {
                button.setImageResource(R.drawable.red);
                isRedTurn = false;
            }
            else {
                button.setImageResource(R.drawable.yellow);
                isRedTurn = true;
            }
            button.setTranslationY(-1500);
            button.animate().translationYBy(1500).rotation(3600).setDuration(1000);
        }
    }

    public int getWinner(){
        int FLAG_RED_WON = 1;
        int FLAG_YELLOW_WON = 2;
        for(int[] state : states){
            if(occupancy[state[0]] + occupancy[state[1]] + occupancy[state[2]] == 3) return FLAG_RED_WON;
            else if (occupancy[state[0]] + occupancy[state[1]] + occupancy[state[2]] == -3) return FLAG_YELLOW_WON;
        }
        return -1;
    }

    public void decideWinner(){
        if(getWinner() > 0) {
            TextView textView = (TextView) findViewById(R.id.textView);
            String message = " is the winner!";
            if (getWinner() == 1)
                message = "Red" + message;
            else if (getWinner() == 2)
                message = "Yellow" + message;
            textView.setText(message);
            gameActive = false;
        }
    }

    public void dropdown(View view){

        ImageView button = (ImageView) view;
        updateStates(button);
        decideWinner();
        if(!gameActive){
            Button playAgainButton = (Button) findViewById(R.id.button);
            playAgainButton.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("");
        Button playAgainButton = (Button) findViewById(R.id.button);
        playAgainButton.setVisibility(View.INVISIBLE);

        isRedTurn = true;
        for(int i=0; i<occupancy.length; i++)
            occupancy[i] = 0;
        gameActive = true;

        androidx.gridlayout.widget.GridLayout gridlayout = findViewById(R.id.gridLayout);
        for(int i = 0; i < gridlayout.getChildCount(); i++){
            ImageView imageView = (ImageView) gridlayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isRedTurn = true;
    }
}