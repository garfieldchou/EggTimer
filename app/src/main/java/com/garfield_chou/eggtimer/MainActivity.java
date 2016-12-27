package com.garfield_chou.eggtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Info", Integer.toString(progress));
                Log.i("Info- min", Integer.toString(progress / 60));
                Log.i("Info- sec", Integer.toString(progress % 60));
                minTextView.setText(String.format("%02d", progress / 60));
                secTextView.setText(String.format("%02d", progress % 60));
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
