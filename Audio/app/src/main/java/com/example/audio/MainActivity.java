package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play(View view){
        if(!isPlaying ) {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    public void pause(View view){
        if(isPlaying){
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.light);

        //AudioManager controls the system volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Initialize seekbar with properties
        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumeSeekBar);
        volumeBar.setMax(maxVolume);
        volumeBar.setProgress(currentVolume);

        //Change AudioManager settings using Seekbar
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar scrubBar = (SeekBar) findViewById(R.id.scrubSeekBar);
        scrubBar.setMax(mediaPlayer.getDuration());
        scrubBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
                isPlaying = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
                isPlaying = true;
            }
        });

        //Timer used to update something with time. Here, used to update seekbar position with audio progress
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 300);
    }
}