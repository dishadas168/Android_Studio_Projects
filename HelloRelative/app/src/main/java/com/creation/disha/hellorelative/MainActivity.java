package com.creation.disha.hellorelative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.creation.disha.hellorelative.R;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;

    private TextView showCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        showCount = (TextView) findViewById(R.id.show_count);
        if (showCount != null)
            showCount.setText(Integer.toString(mCount));
    }
}
