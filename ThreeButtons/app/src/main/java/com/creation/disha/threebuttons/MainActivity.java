package com.creation.disha.threebuttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*private Button button1;
    private Button button2;
    private Button button3;*/

    public static final String EXTRA_TEXT_1 = "All my loving, I'll give to you....";
    public static final String EXTRA_TEXT_2 = "No where you can be that is where you're not meant to be, it's easy!";
    public static final String EXTRA_TEXT_3 = "Let me take you down coz I'm going to....";

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);*/
        intent = new Intent(this, DisplayText.class);
    }

    public void onClickButton1(View view) {
        intent.putExtra("text", EXTRA_TEXT_1);
        startActivity(intent);
    }

    public void onClickButton2(View view) {
        intent.putExtra("text", EXTRA_TEXT_2);
        startActivity(intent);
    }

    public void onClickButton3(View view) {
        intent.putExtra("text", EXTRA_TEXT_3);
        startActivity(intent);
    }
}
