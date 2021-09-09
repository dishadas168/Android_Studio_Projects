package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void switchImage(View view){
        ImageView pic1 = (ImageView) findViewById(R.id.imageView);
        ImageView pic2 = (ImageView) findViewById(R.id.imageView2);
        Log.i("YOOO", "BUTton clicked");

        if(pic1.getVisibility() == View.VISIBLE){
            pic1.setVisibility(View.INVISIBLE);
            pic2.setVisibility(View.VISIBLE);
        }else{
            pic2.setVisibility(View.INVISIBLE);
            pic1.setVisibility(View.VISIBLE);
        }
        //Change ImageResource
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}