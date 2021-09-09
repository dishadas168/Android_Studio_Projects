package com.example.animations;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SuperSaiyan ss = new SuperSaiyan();

    class SuperSaiyan{
        private int counter;
        private List<String> transformations = new ArrayList<String>();

        public SuperSaiyan(){
            this.counter = 1;
            this.transformations.add("goku");
            this.transformations.add("goku_ss2");
            this.transformations.add("goku_ss3");
            this.transformations.add("goku_ss4");
            this.transformations.add("goku_ss5");
            this.transformations.add("goku_blue");
        }

        public void animate(ImageView transformImage, int duration){
            transformImage.animate().alpha(0).setDuration(duration);
            String name = this.transformations.get(this.counter);
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            Log.i("HHHHHHHH", String.valueOf(id));
            transformImage.setImageResource(counter);
            transformImage.animate().alpha(1).setDuration(duration);
            this.counter++;
            if (this.counter == 5) this.counter = 0;
        }
    }

    public void switchImage(View view){
        ImageView transformImage = (ImageView) findViewById(R.id.transformImage);
        ss.animate(transformImage, 5000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}