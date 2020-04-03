package com.example.sumit.holdyourbreath;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;

import static com.example.sumit.holdyourbreath.R.id.chronometer1;

public class main extends AppCompatActivity {
    private Chronometer chronometerB;
    public boolean running;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chronometer chronometerB =(Chronometer) findViewById(chronometer1);
        //chronometer.start();

    }
    /** Called when the user touches the button */
    public void StartChronometer(View v)
    {
        if(!running) {
            //((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());
            ((Chronometer) findViewById(chronometer1)).start();
            running =true;

        }

    }

    public void pauseChronometer(View v)
    {
        if(running) {
            // ((Chronometer) findViewById(chronometer1)).stop();
            ((Chronometer) findViewById(chronometer1)).stop();
            running=false;
        }


    }

    public void resetChronometer(View v)
    {
                   ((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());
        running=false;
            }
}
