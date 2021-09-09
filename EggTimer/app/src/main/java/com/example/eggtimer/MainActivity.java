package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    MediaPlayer sound;
    CountDownTimer timer;
    Button button;
    boolean isPlaying = false;


    public void setTimeOnTextView(int progress){
        textView.setText(String.format("%d:%02d", progress/60, progress%60));
    }

    public void reset(){
        seekBar.setProgress(30);
        textView.setText("0:30");
        button.setText("GO!");
        isPlaying = false;
    }

    public void timerStart(int currentStartTime){
        button.setText("STOP!");
        isPlaying = true;
        timer = new CountDownTimer(currentStartTime*1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                setTimeOnTextView((int)millisUntilFinished/1000);
            }
            @Override
            public void onFinish(){
                sound.start();
                reset();
            }
        }.start();
    }

    public void timerPause(){
        timer.cancel();
    }

    public void timerStartStop(View view){
        if(isPlaying){
            timerPause();
            reset();
        }else
            timerStart(seekBar.getProgress());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        sound = MediaPlayer.create(this, R.raw.sound);
        button = (Button) findViewById(R.id.button);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(300);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(!isPlaying)
                    setTimeOnTextView(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}