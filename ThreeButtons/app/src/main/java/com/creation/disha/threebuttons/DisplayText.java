package com.creation.disha.threebuttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);

        Intent text = getIntent();
        String mText = text.getStringExtra("text");

        TextView display = (TextView)findViewById(R.id.textView);
        display.setText(mText);
    }
}
