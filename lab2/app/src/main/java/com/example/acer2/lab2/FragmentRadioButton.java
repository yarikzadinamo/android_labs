package com.example.acer2.lab2;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class FragmentRadioButton extends Fragment {

OnFragmentInteractionListener mListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_radioButton, container, false);

        try {

            mListener = (OnFragmentInteractionListener) view.getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(view.getContext().toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }

        Button b = view.findViewById(R.id.ok);






        b.setOnClickListener((View v) ->{
            RadioButton rb1 = view.findViewById(R.id.but1);
            RadioButton rb2 = view.findViewById(R.id.but2);

            if(rb1.isChecked()){
                mListener.onFragmentInteraction("serif");

            }
            else if(rb2.isChecked()){
                mListener.onFragmentInteraction("sans");

            }

        });

        Button b2 = view.findViewById(R.id.cancel);

        b2.setOnClickListener((View v)->{
            System.exit(0);
        });
        return inflater.inflate(R.layout.fragment_radioButton, container, false);

    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

}
