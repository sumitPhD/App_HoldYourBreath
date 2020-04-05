package com.example.sumit.holdyourbreath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class user extends AppCompatActivity {
    Button btn_start;
    EditText edit_add_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_start = (Button)findViewById(R.id.btn_start_user);
        edit_add_name= (EditText) findViewById(R.id.editText_name);
        start_main();
        //add_name_db(); // from edittext to the db database name

        /*DatabaseHelper info = new DatabaseHelper(this);
        info.setString("kbc");*/
        //info.setString(1,"kbc");
    }


    public void start_main(){
        btn_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(v.getContext(),main.class);
                        Intent intent = new Intent(user.this,main.class);
                            //Integer val1 = Integer.parseInt(Text_breath1.getText().toString());
                        //Intent intent1 = new Intent(user.this,DatabaseHelper.class);
                        String edit_name = edit_add_name.getText().toString();
                        intent.putExtra("name",edit_name);
                        //intent1.putExtra("name","abc");


                        startActivity(intent);
                        }

                }
        );
    }


    /*public void add_name_db(){
        btn_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dbName =  edit_add_name.getText().toString();
                        Intent intent = new Intent(v.getContext(),DatabaseHelper.class);
                        intent.putExtra("name",dbName);
                    }

                }
        );
    }*/

}
