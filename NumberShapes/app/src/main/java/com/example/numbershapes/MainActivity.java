package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Math.sqrt;

class Number{

    private int number;
    private boolean isTriangular;
    private boolean isSquare;

    public Number(int num){
        this.number = num;
        this.isTriangular = this.checkIfTriangular();
        this.isSquare = this.checkIfSquare();
    }

    private boolean isInteger(double num){
        if(Math.floor(num) == num) return true;
        else return false;
    }

    private boolean checkIfSquare(){
        if(isInteger(sqrt(this.number))) return true;
        else return false;
    }

    private boolean checkIfTriangular(){

        int c = -2*this.number;
        int d = 1 - 4*c;

        if(d <= 0 )
            return false;

        double root1 = (-1 + sqrt(d))/2;
        double root2 = (-1 - sqrt(d))/2;

        if(isInteger(root1) && isInteger(root2))
            return true;
        else
            return false;
    }

    public boolean isTriangular(){return this.isTriangular;}

    public boolean isSquare(){return this.isSquare;}
}

public class MainActivity extends AppCompatActivity {

    private String getShapeMessage(int num){

        Number number = new Number(num);
        String message = Integer.toString(num);

        if(number.isTriangular() && number.isSquare())
            message += " is triangular and square.";
        else if (number.isTriangular())
            message += " is triangular.";
        else if (number.isSquare())
            message += " is square.";
        else
            message += " is neither triangular nor square.";

        return message;
    }

    public void getShape(View view){
        EditText numberEditText = (EditText) findViewById(R.id.editTextNumber);
        String numString = numberEditText.getText().toString();

        String message;
        if(numString.isEmpty()){
            message = "Please enter a number.";
        }else{
            message = getShapeMessage(Integer.parseInt(numberEditText.getText().toString()));
        }
        numberEditText.getText().clear();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}