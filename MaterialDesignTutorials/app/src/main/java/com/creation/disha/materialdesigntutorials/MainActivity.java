package com.creation.disha.materialdesigntutorials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDashboardDesign(View view) {
        Intent intent = new Intent(this, DashboardDesign.class);
        startActivity(intent);
    }

    public void goToNavigationDrawer(View view) {
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
    }
}
