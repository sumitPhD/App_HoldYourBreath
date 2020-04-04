package com.example.sumit.holdyourbreath;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.v7.app.AlertDialog;
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
    public boolean running, discard;
    EditText editText_breath1,editText_breath2,editText_breath3;
    TextView Text_breath1, Text_breath2, Text_breath3,Text_avg;
    Button btn_addData;
    Button btn_ViewAlldata;
    Button btn_setAlarm;
    Button btn_viewsStats;
    Button button_startB, button_stopB,button_discardB ;
    int count_run = 0;
    int avg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        Text_breath1 = (TextView) findViewById(R.id.text_breath1);
        Text_breath2 = (TextView) findViewById(R.id.text_breath2);
        Text_breath3 = (TextView) findViewById(R.id.text_breath3);
        Text_avg = (TextView) findViewById(R.id.text_avg);

        btn_addData = (Button)findViewById(R.id.btn_addData);
        btn_ViewAlldata = (Button)findViewById(R.id.btn_ViewAlldata);
        btn_setAlarm = (Button)findViewById(R.id.btn_setAlarm);
        btn_viewsStats = (Button)findViewById(R.id.btn_viewsStats);
        button_startB = (Button)findViewById(R.id.button_startB);
        button_stopB = (Button)findViewById(R.id.button_stopB);
        button_discardB = (Button)findViewById(R.id.button_discardB);
        Chronometer chronometerB = (Chronometer) findViewById(chronometer1);
        AddData();
        viewAll();
        setAlarm();
        viewStats();
        startBreath();
        stopBreath();
        discardBreath();

        //chronometer.start();


    }

    public void setAlarm(){
        btn_setAlarm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        intent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,"Reminder for Breath Hold Capacity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void viewStats(){
        btn_viewsStats.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(),stats.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void startBreath(){
        button_startB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!running) {
                            ((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());
                            ((Chronometer) findViewById(chronometer1)).start();
                            running = true;
                        }
                    }
                }
        );
    }

    public void stopBreath(){
        button_stopB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(running) {
                            ((Chronometer) findViewById(chronometer1)).stop();

                            long elapsedMillis = SystemClock.elapsedRealtime() - ((Chronometer) findViewById(chronometer1)).getBase();
                            int seconds = (int)(elapsedMillis/1000 % 60);
                            //((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());

                            running = false;
                            discard = true;
                            if (count_run == 0) {
                                setBreath1(seconds);
                                count_run =1;
                            } else if (count_run == 1) {
                                setBreath2(seconds);
                                count_run=2;
                            } else if (count_run == 2) {
                                setBreath3(seconds);
                                Toast.makeText(main.this, "Done for now",
                                        Toast.LENGTH_SHORT).show();
                            count_run =3;}
                        }

                    }
                }
        );
    }

    public void discardBreath(){
        button_discardB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!running & discard){
                            //((Chronometer) findViewById(chronometer1)).setBase(SystemClock.elapsedRealtime());
                            discard = false;
                            if (count_run == 1) {
                                clearBreath1();
                                count_run=0;
                            } else if (count_run == 2) {
                                clearBreath2();
                                count_run=1;
                            } else if (count_run == 3) {
                                clearBreath3();
                                count_run=2;
                            }
                        }
                        }

                }
        );
    }


    private void showElapsedTime() {
            long elapsedMillis = SystemClock.elapsedRealtime() - ((Chronometer) findViewById(chronometer1)).getBase();
            Toast.makeText(main.this, "Elapsed milliseconds: " + elapsedMillis,
                    Toast.LENGTH_SHORT).show();
        }




    private void setBreath1(int seconds) {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath1);
        BreathTextView1.setText(String.valueOf(seconds));}
    private void clearBreath1() {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath1);
        BreathTextView1.setText("");}

    private void setBreath2(int seconds) {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath2);
        BreathTextView1.setText(String.valueOf(seconds));}
    private void clearBreath2() {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath2);
        BreathTextView1.setText("");}

    private void setBreath3(int seconds) {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath3);
        BreathTextView1.setText(String.valueOf(seconds));}
    private void clearBreath3() {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_breath3);
        BreathTextView1.setText("");}
    private void setavg(int avg) {
        TextView BreathTextView1 = (TextView) findViewById(R.id.text_avg);
        BreathTextView1.setText(String.valueOf(avg));}

    public void AddData(){
        btn_addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count_run == 3) {
                            /*Double val1 = Double.parseDouble(Text_breath1.getText().toString());
                            Double val2 = Double.parseDouble(Text_breath2.getText().toString());
                            Double val3 = Double.parseDouble(Text_breath3.getText().toString());
                            Double avg = ((val1+val2+val3));*/
                            Integer val1 = Integer.parseInt(Text_breath1.getText().toString());
                            Integer val2 = Integer.parseInt(Text_breath2.getText().toString());
                            Integer val3 = Integer.parseInt(Text_breath3.getText().toString());
                            Integer avg = ((val1+val2+val3))/3;
                            setavg(avg);

                            boolean isInserted = myDb.insertData(Text_breath1.getText().toString(),
                                    Text_breath2.getText().toString(),
                                    Text_breath3.getText().toString(),
                                    Text_avg.getText().toString());
                            if (isInserted == true){
                                Toast.makeText(main.this, "Data Insterted", Toast.LENGTH_LONG).show();
                                Toast.makeText(main.this, "You can take next test", Toast.LENGTH_LONG).show();
                                clearBreath1();
                                clearBreath2();
                                clearBreath3();
                                count_run = 0;}
                            else{
                                Toast.makeText(main.this, "Data is not Insterted", Toast.LENGTH_LONG).show();

                            }
                        }

                        else{
                            Toast.makeText(main.this, "First finish all tests", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }

    public void viewAll(){
        btn_ViewAlldata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                                // show data
                            showMessage("Error", "No data found");

                            return;
                            }
                        StringBuffer buffer =  new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Breath1:"+ res.getString(1)+"\n");
                            buffer.append("Breath2 :"+ res.getString(2)+"\n");
                            buffer.append("Breath3 :"+ res.getString(3)+"\n\n");
                            buffer.append("Average :"+ res.getString(4)+"\n\n");
                            buffer.append("Date :"+ res.getString(5)+"\n\n");

                        }

                        showMessage("Data",buffer.toString() );

                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}





