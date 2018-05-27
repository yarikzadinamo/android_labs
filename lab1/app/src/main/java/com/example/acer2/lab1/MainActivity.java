package com.example.acer2.lab1;

import android.app.Application;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.ok);


        tw = findViewById(R.id.text1);



        b.setOnClickListener((View v) ->{
            RadioButton rb1 = findViewById(R.id.but1);
            RadioButton rb2 = findViewById(R.id.but2);

            if(rb1.isChecked()){

                tw.setTypeface(Typeface.SERIF);
            }
            else if(rb2.isChecked()){

                tw.setTypeface(Typeface.SANS_SERIF);
            }

        });

        Button b2 = findViewById(R.id.cancel);

        b2.setOnClickListener((View v)->{
            finish();
            System.exit(0);
        });
    }



}
