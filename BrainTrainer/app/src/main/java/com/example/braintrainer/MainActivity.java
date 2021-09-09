package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.net.LinkProperties;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView question;
    TextView successRate;
    TextView result;
    Random random;
    int answerIndex;
    int totalAnswered;
    int correctlyAnswered;
    boolean answered = false;
    androidx.gridlayout.widget.GridLayout gridLayout;

    boolean inProgress;

    public void startTimer(){
        inProgress = true;
        //Adding 100 ms keeps the timer reach the end at the moment it hits 0
        new CountDownTimer(30000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText((int)millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                result.setText("Done!");
                inProgress = false;
            }
        }.start();
    }

    public void updateQuestion(){
        int num1 = 0;
        int num2 = 0;
        int answer;
        Button butt;

        answerIndex = random.nextInt(4);

        for(int i = 0; i < gridLayout.getChildCount(); i++){
            butt = (Button) gridLayout.getChildAt(i);
            butt.setText(String.valueOf(random.nextInt(100) + 1));
            if(answerIndex == i) {
                answer = Integer.parseInt(butt.getText().toString());
                num1 = random.nextInt(answer - 1) + 1;
                num2 = answer - num1;
            }
        }
        question.setText(num1 + " + " + num2);
    }

    public void updateScore(){
        successRate.setText(correctlyAnswered+ "/" + totalAnswered);
    }

    public void playGame(){
        startTimer();
        updateScore();
        updateQuestion();
    }

    public void playAgain(View view){
        totalAnswered = 0;
        correctlyAnswered = 0;
        result.setText("");
        view.setVisibility(View.INVISIBLE);
        playGame();
    }

    public void isThatCorrect(View view){
        if(inProgress){
            totalAnswered ++;
            if(Integer.parseInt(view.getTag().toString()) == answerIndex){
                correctlyAnswered ++;
                result.setText("Correct!");
            }else{
                result.setText("Wrong :(");
            }
            updateScore();
            updateQuestion();
        }

    }

    public void startGame(View view){
        Button playButton = (Button) findViewById(R.id.playButton);

        playButton.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        successRate.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timerTextView);
        question = (TextView) findViewById(R.id.questionTextView);
        successRate = (TextView) findViewById(R.id.successRateTextView);
        result = (TextView) findViewById(R.id.resultTextView);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        random = new Random();
    }
}