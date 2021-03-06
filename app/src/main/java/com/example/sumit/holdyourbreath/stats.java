package com.example.sumit.holdyourbreath;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



public class stats extends AppCompatActivity {
    Button view_a_bar ;

    DatabaseHelper myDb = main.myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        view_a_bar = (Button)findViewById(R.id.view_a_bar);

        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int bar_count =0;

        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            // show data
            showMessage("Error", "No data found");

            return;
        }
        //StringBuilder buffer =  new StringBuilder();
        while (res.moveToNext()) {
            Integer avg_val = Integer.parseInt(res.getString(4));
            entries.add(new BarEntry(avg_val, bar_count));
            labels.add(res.getString(5));
            bar_count++;


            BarDataSet bardataset = new BarDataSet(entries, "Cells");


            BarData data = new BarData(labels, bardataset);
            barChart.setData(data); // set the data and list of lables into chart
//Add these two lines after setting the data
            barChart.setVisibleXRangeMaximum(8);
            barChart.setVisibleXRangeMinimum(8);

            barChart.setDescription("Breath Capacity");  // set the description

            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

            barChart.animateY(1000);

            viewAll();

        }
    }

    public void viewAll(){
        view_a_bar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                            // show data
                            showMessage("Error", "No data found");

                            return;
                        }
                        StringBuilder buffer =  new StringBuilder();
                        while (res.moveToNext()){
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