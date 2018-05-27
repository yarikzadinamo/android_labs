package com.example.acer2.lab2;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class lab2 extends AppCompatActivity  implements FragmentRadioButton.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

       }

    @Override
    public void onFragmentInteraction(String link) {
        FragmentText fragment = (FragmentText) getFragmentManager().findFragmentById(R.id.fragmentText);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }
}
