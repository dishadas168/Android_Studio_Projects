package com.example.exampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void login(View view){
        EditText username = (EditText) findViewById(R.id.usernameEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);

        Log.i("Info", "Login Button clicked!");
        Log.i("Username", username.getText().toString());
        Log.i("Password", password.getText().toString());

        Toast.makeText(this, "Hello "+ username.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void clickFunction(View view){

        EditText nameEditText = (EditText)findViewById(R.id.nameEditText);

        Log.i("Info: ", "Button clicked!");
        Log.i("Value: ", nameEditText.getText().toString());

        Toast.makeText(this, "Hello " + nameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}