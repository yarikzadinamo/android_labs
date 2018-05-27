package com.example.acer2.lab3;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class txtFragment extends Fragment {
    private TextView textView;

    final static String textViewKey = "OUTPUT_TEXTVIEW_TEXT";

 public void setMessage(String message, Typeface typeface) {

        textView.setTypeface(typeface);

        textView.setText(message);

    }

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.txt_fragment, container, false);

        textView = view.findViewById(R.id.output);

        if (savedInstanceState != null) {

            String savedText = savedInstanceState.getString(textViewKey);
            textView.setText(savedText);
        }

        return view;
    }



}
