package com.creation.disha.shoppinglistbuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Shop extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    public void onClick(View v){
        Button clicked = (Button) findViewById(v.getId());
        String text = clicked.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("addToList", text);
        setResult(RESULT_OK, intent);
        finish();
    }
}
