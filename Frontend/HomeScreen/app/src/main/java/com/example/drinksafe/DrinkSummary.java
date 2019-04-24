package com.example.drinksafe;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Jorden Lee
 * @author Philip Payne
 */
public class DrinkSummary extends AppCompatActivity {

    private TextView ds_hours,ds_mins, ds_sec;
    private long hours,mins,sec;

    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_summary);

        ds_hours = findViewById(R.id.ds_hour);
        ds_mins = findViewById(R.id.ds_min);
        ds_sec = findViewById(R.id.ds_sec);

        new CountDownTimer(4500000, 1000) {

            public void onTick(long millisUntilFinished) {
                convertTime(millisUntilFinished);
                String h = Long.toString(hours);
                String m = Long.toString(mins);
                String s = Long.toString(sec);
                ds_hours.setText(h);
                ds_mins.setText(m);
                ds_sec.setText(s);
            }

            public void onFinish() {

            }
        }.start();
    }

    private void convertTime(long ms) {
        long tmp = ms;
        hours = tmp / 3600000;
        tmp -= hours * 3600000;
        mins = tmp / 60000;
        tmp -= mins * 60000;
        sec = tmp / 1000;
        if(tmp != 0) {

        }
    }
}
