package com.garfield_chou.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.garfield_chou.eggtimer.R.id.timerSeekBar;

public class MainActivity extends AppCompatActivity {

    long countDownStart = 0;
    CountDownTimer timerCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        final TextView minTextView = (TextView) findViewById(R.id.minTextView);
        final TextView secTextView = (TextView) findViewById(R.id.secTextView);

        timerSeekBar.setMax(600); // max: 10 mins
        minTextView.setText("03");
        timerSeekBar.setProgress(180);
        countDownStart = 180000;
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Info", Integer.toString(progress));
                Log.i("Info- min", Integer.toString(progress / 60));
                Log.i("Info- sec", Integer.toString(progress % 60));
                minTextView.setText(String.format("%02d", progress / 60));
                secTextView.setText(String.format("%02d", progress % 60));
                countDownStart = (long)(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void goClick (View view) {
        final TextView minTextView = (TextView) findViewById(R.id.minTextView);
        final TextView secTextView = (TextView) findViewById(R.id.secTextView);
        Log.i("goClick", "tapped!");

        timerCountDown = new CountDownTimer(countDownStart, 1000) {
            @Override
            public void onTick(long millisecondsUntilDone) {
                Log.i("Countdown to...", Long.toString(millisecondsUntilDone / 1000));
                minTextView.setText(String.format("%02d", (millisecondsUntilDone /1000) / 60));
                secTextView.setText(String.format("%02d", (millisecondsUntilDone /1000) % 60));
            }

            @Override
            public void onFinish() {

            }
        }.start();
        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerSeekBar.setEnabled(false);

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.INVISIBLE);

        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setVisibility(View.VISIBLE);
    }

    public void stopClick (View view) {
        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setVisibility(View.INVISIBLE);

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        
        timerCountDown.cancel();
        TextView minTextView = (TextView) findViewById(R.id.minTextView);
        TextView secTextView = (TextView) findViewById(R.id.secTextView);
        minTextView.setText("03");
        secTextView.setText("00");
        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerSeekBar.setProgress(180);
        countDownStart = 180000;
        timerSeekBar.setEnabled(true);
    }
}
