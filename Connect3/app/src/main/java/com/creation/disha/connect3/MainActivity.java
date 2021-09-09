package com.creation.disha.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    // 0= cross. 1=circle
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int active = Integer.parseInt(view.getTag().toString());

        if(gameState[active]== 2) {

            gameState[active]= activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(90).setDuration(300);
        }
    }
}
