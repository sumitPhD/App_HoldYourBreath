package com.example.sumit.holdyourbreath;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sumit.holdyourbreath.R.id.chronometer1;

public class main extends AppCompatActivity {
    DatabaseHelper myDb;
    private Chronometer chronometerB;
    public boolean running;
    EditText editText_breath1,editText_breath2,editText_breath3;
    Button btn_addData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editText_breath1 = (EditText) findViewById(R.id.editText_breath1);
        editText_breath2 = (EditText) findViewById(R.id.editText_breath2);
        editText_breath3 = (EditText) findViewById(R.id.editText_breath3);
        btn_addData = (Button)findViewById(R.id.btn_addData);

        //Displaying Toast with Hello Javatpoint message
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_LONG).show();

        Chronometer chronometerB = (Chronometer) findViewById(chronometer1);
        AddData();
        //chronometer.start();
    }



    private void showElapsedTime() {
            long elapsedMillis = SystemClock.elapsedRealtime() - ((Chronometer) findViewById(chronometer1)).getBase();
            Toast.makeText(main.this, "Elapsed milliseconds: " + elapsedMillis,
                    Toast.LENGTH_SHORT).show();
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
            long elapsedMillis = SystemClock.elapsedRealtime() - ((Chronometer) findViewById(chronometer1)).getBase();
            int seconds = (int)(elapsedMillis/1000 % 60);
            //Intent intent = new Intent(getApplicationContext(),
              //      main.class).putExtra("timer",seconds);
            //startActivity(intent);
            display(seconds);

        }


    }
    private void display(int seconds) {
        TextView quantityTextView = (TextView) findViewById(R.id.textTime);
        quantityTextView.setText(String.valueOf(seconds));}

    public void resetChronometer(View v) {
        ((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());
        running = false;
    }

    public void AddData(){
        btn_addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean isInserted =    myDb.insertData(editText_breath1.getText().toString(),
                             editText_breath2.getText().toString(),
                             editText_breath3.getText().toString());
                        if (isInserted=true)
                            Toast.makeText(main.this,"Data Insterted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(main.this,"Data is not Insterted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }



}





