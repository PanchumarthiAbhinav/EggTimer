package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar countseekbar;
    TextView countdown;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    Boolean val = false;
    public void countdownbutton(View view){
        view.setVisibility(View.INVISIBLE);
        Button stop = (Button) findViewById(R.id.stopbutton);
        stop.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(countseekbar.getProgress()*1000,1000) {
            @Override
            public void onTick(long l) {
                updatecountdown((int) l/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void startaudio(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.countdown);
        mediaPlayer.start();
        val = true;
    }
    public void updatecountdown(Integer seconds){
        Integer minutes = seconds/60;
        Integer seconds1 = seconds-(minutes*60);
        String finalseconds = Integer.toString(seconds1);
        if(minutes == 0 && seconds1 == 10){
            startaudio();
        }
        if(seconds1>=0 && seconds1<10){
            finalseconds = "0"+finalseconds;
        }
        countdown = (TextView) findViewById(R.id.counttextView);
        countdown.setText(Integer.toString(minutes)+":"+finalseconds);
    }
    public void stopbutton(View view){
        if(val)
            mediaPlayer.pause();
        view.setVisibility(View.INVISIBLE);
        countDownTimer.cancel();
        updatecountdown(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countseekbar = (SeekBar) findViewById(R.id.timerseekBar);
        countseekbar.setMax(600);
        countseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatecountdown(i);
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