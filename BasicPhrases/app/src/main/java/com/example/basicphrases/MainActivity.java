package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    int[] soundList = new int[8];

    public void getAudio(View view){
        int tag = Integer.valueOf(view.getTag().toString());
        mediaPlayer = MediaPlayer.create(this, soundList[tag - 1]);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundList[0] = R.raw.hello;
        soundList[1] = R.raw.howareyou;
        soundList[2] = R.raw.goodevening;
        soundList[3] = R.raw.doyouspeakenglish;
        soundList[4] = R.raw.ilivein;
        soundList[5] = R.raw.mynameis;
        soundList[6] = R.raw.please;
        soundList[7] = R.raw.welcome;
    }
}