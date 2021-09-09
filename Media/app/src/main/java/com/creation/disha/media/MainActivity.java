package com.creation.disha.media;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fade (View view){

        ImageView bart = (ImageView) findViewById(R.id.bart);
        ImageView homer = (ImageView) findViewById(R.id.homer);

       if (bart.getAlpha() > 0){
           bart.animate().alpha(0f).setDuration(2000);
           homer.animate().alpha(1f).setDuration(2000);
       }else{
           bart.animate().alpha(1f).setDuration(2000);
           homer.animate().alpha(0f).setDuration(2000);
       }
    }

}
